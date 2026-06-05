package com.example.appevent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.appevent.data.local.entity.FavoriteEvent
import com.example.appevent.ui.viewmodel.FavoriteViewModel
import com.example.appevent.ui.viewmodel.FavoriteViewModelFactory
import com.example.appevent.ui.viewmodel.DetailViewModel
import com.example.appevent.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels { FavoriteViewModelFactory(application) }

    private lateinit var eventImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var ownerTextView: TextView
    private lateinit var timeTextView: TextView
    private lateinit var quotaTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var linkButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var favoriteButton: FloatingActionButton

    // --- TAMBAHAN TOOLBAR 1: Deklarasi Variabel ---
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var collapsingToolbar: com.google.android.material.appbar.CollapsingToolbarLayout
    // ----------------------------------------------

    private lateinit var btnSummarizeAI: com.google.android.material.button.MaterialButton
    private lateinit var cardAiResult: com.google.android.material.card.MaterialCardView
    private lateinit var tvAiSummary: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        eventImageView = findViewById(R.id.imageLogo)
        nameTextView = findViewById(R.id.eventName)
        ownerTextView = findViewById(R.id.eventOwner)
        timeTextView = findViewById(R.id.eventTime)
        quotaTextView = findViewById(R.id.eventQuota)
        descriptionTextView = findViewById(R.id.eventDescription)
        linkButton = findViewById(R.id.eventLinkButton)
        progressBar = findViewById(R.id.progressBar)
        favoriteButton = findViewById(R.id.btnFavorite)

        btnSummarizeAI = findViewById(R.id.btnSummarizeAI)
        cardAiResult = findViewById(R.id.cardAiResult)
        tvAiSummary = findViewById(R.id.tvAiSummary)

        // --- TAMBAHAN TOOLBAR 2: Inisialisasi & Logika Tombol Back ---
        toolbar = findViewById(R.id.toolbar)
        collapsingToolbar = findViewById(R.id.collapsingToolbar)

        // Atur toolbar kustom menjadi ActionBar utama
        setSupportActionBar(toolbar)

        // Tampilkan panah kembali (Back Button)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Beri perintah pada panah kembali untuk menutup halaman
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        // -------------------------------------------------------------

        val eventId = intent.getIntExtra("EVENT_ID", -1)
        if (eventId != -1) {
            detailViewModel.fetchEventDetail(eventId)
        }

        observeViewModel()
        setupFavoriteButton(eventId)
    }

    private fun observeViewModel() {
        detailViewModel.eventDetail.observe(this) { event ->
            if (event != null) {
                Glide.with(this).load(event.imageLogo).into(eventImageView)
                nameTextView.text = event.name

                // --- TAMBAHAN TOOLBAR 3: Pasang Judul di CollapsingToolbar ---
                // Agar saat di-scroll ke atas, nama event muncul di toolbar
                collapsingToolbar.title = event.name
                // -------------------------------------------------------------

                ownerTextView.text = event.ownerName
                timeTextView.text = "Waktu: ${event.beginTime} - ${event.endTime}"
                quotaTextView.text = "Sisa Kuota: ${event.quota - event.registrants}"
                descriptionTextView.text = HtmlCompat.fromHtml(
                    event.description,
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
                btnSummarizeAI.setOnClickListener {
                    // Kita hapus tag HTML dari deskripsi agar AI tidak bingung
                    val plainDescription = descriptionTextView.text.toString()
                    generateAiSummary(plainDescription)
                }
                linkButton.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.link))
                    startActivity(intent)
                }
                progressBar.visibility = View.GONE
            }
        }

        detailViewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        detailViewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun setupFavoriteButton(eventId: Int) {
        favoriteViewModel.checkFavoriteStatus(eventId)

        favoriteViewModel.isFavorite.observe(this) { isFavorite ->
            updateFavoriteIcon(isFavorite)
        }

        favoriteButton.setOnClickListener {
            detailViewModel.eventDetail.value?.let { event ->
                val favoriteEvent = FavoriteEvent(
                    id = event.id,
                    name = event.name,
                    ownerName = event.ownerName,
                    beginTime = event.beginTime,
                    endTime = event.endTime,
                    quota = event.quota,
                    imageLogo = event.mediaCover, // Menggunakan mediaCover dari respons API
                    description = event.description,
                    link = event.link,
                    registrants = event.registrants
                )
                favoriteViewModel.toggleFavoriteStatus(favoriteEvent)
            }
        }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            favoriteButton.setImageResource(R.drawable.ic_favorite)
        } else {
            favoriteButton.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    private fun generateAiSummary(fullDescription: String) {
        // 1. Munculkan kotak loading
        cardAiResult.visibility = View.VISIBLE
        tvAiSummary.text = "Mengumpulkan informasi... ✨"
        btnSummarizeAI.isEnabled = false // Matikan tombol sementara

        // 2. Siapkan Model Gemini
        val generativeModel = GenerativeModel(
            modelName = "gemini-3.5-flash",
            apiKey = BuildConfig.GEMINI_API_KEY
        )

        // 3. Beri perintah (Prompt) yang sangat spesifik
        val prompt = """
            Tolong baca deskripsi acara berikut ini dan buatkan ringkasan singkat dalam bahasa Indonesia.
            Buat ke dalam 3 poin (*bullet points*) saja:
            1. Tentang apa acara ini?
            2. Siapa yang cocok ikut acara ini?
            3. Apa manfaat utamanya?
            
            Deskripsi acara:
            $fullDescription
        """.trimIndent()

        // 4. Jalankan tugas di latar belakang agar HP tidak nge-lag
        // 4. Jalankan tugas di latar belakang
        lifecycleScope.launch {
            try {
                val response = generativeModel.generateContent(prompt)
                val rawText = response.text ?: "Tidak ada ringkasan."

                // SIHIR PENGUBAH MARKDOWN KE HTML
                // 1. Ubah **Teks** menjadi <b>Teks</b> (Tebal)
                var processedText = rawText.replace(Regex("\\*\\*(.*?)\\*\\*"), "<b>$1</b>")
                // 2. Ubah bintang tunggal (* ) menjadi simbol peluru (•)
                processedText = processedText.replace(Regex("\\*\\s"), "&#8226; ")
                // 3. Ubah enter/baris baru menjadi tag <br>
                processedText = processedText.replace("\n", "<br>")

                // 4. Tampilkan ke layar menggunakan HtmlCompat
                tvAiSummary.text = androidx.core.text.HtmlCompat.fromHtml(
                    processedText,
                    androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
                )

            } catch (e: Exception) {
                // Jika gagal (internet mati, dsb)
                tvAiSummary.text = "Waduh, AI-nya sedang lelah: ${e.message}"
            } finally {
                btnSummarizeAI.isEnabled = true // Nyalakan tombol lagi
            }
        }
    }
}