# 📱 Smart Event Explorer (with Gemini AI)

Aplikasi Android modern berbasis Kotlin untuk menjelajahi, menyimpan, dan merangkum informasi acara (event) teknologi secara cerdas. Dibangun dengan mematuhi prinsip Clean Architecture (MVVM) dan standar antarmuka Material Design untuk memberikan pengalaman pengguna yang mulus dan premium.

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Room Database](https://img.shields.io/badge/Room_Database-SQLite-blue?style=for-the-badge)
![Gemini AI](https://img.shields.io/badge/Gemini_AI-Google-orange?style=for-the-badge)

## ✨ Fitur Unggulan (Key Features)

* 🧠 **Smart AI Summarizer (Integrasi Gemini 3.5 Flash)**
    Pengguna tidak perlu membaca deskripsi acara yang terlalu panjang. Hanya dengan satu ketukan, sistem memanggil Google Generative AI untuk memberikan 3 poin ringkasan padat (Tentang Acara, Target Peserta, dan Manfaat) secara *real-time*.
* 💾 **Offline-First Favorites (Room Database)**
    Implementasi arsitektur penyimpanan lokal yang kokoh. Pengguna dapat menyimpan acara favorit mereka dan mengaksesnya kapan saja di tab Favorite, bahkan tanpa koneksi internet sama sekali.
* 🚀 **Modern & Fluid UI/UX**
    * **Shimmer Effect:** Transisi *loading* menggunakan Facebook Shimmer Skeleton, menggantikan *spinner* tradisional.
    * **Swipe to Refresh:** Sinkronisasi ulang data secara mulus dengan gerakan tarik-ke-bawah.
    * **Parallax Scrolling:** Animasi gambar poster yang interaktif saat pengguna menggulir halaman detail.
    * **Material Elevation:** Desain kartu timbul (*drop-shadow*) yang memberikan kedalaman visual dan hierarki yang jelas.

## 🛠️ Tech Stack & Architecture

Aplikasi ini menggunakan teknologi pengembangan Android mutakhir:
* **Architecture:** MVVM (Model-View-ViewModel) untuk pemisahan *logic* dan UI yang bersih.
* **Networking:** Retrofit2 & OkHttp3 (RESTful API).
* **Asynchronous:** Kotlin Coroutines & `lifecycleScope` untuk operasi *background* yang ringan.
* **Local Storage:** Room Persistence Library (SQLite Abstraction).
* **AI Integration:** `com.google.ai.client.generativeai` (Gemini API).
* **Image Loading:** Glide.

## 📸 Cuplikan Layar (Screenshots)
*(Tambahkan gambar cuplikan layar aplikasi Anda di sini nanti)*
| Home Screen | AI Summarizer | Offline Favorites |
| :---: | :---: | :---: |
| <img src="link_gambar_1" width="200"/> | <img src="link_gambar_2" width="200"/> | <img src="link_gambar_3" width="200"/> |

## 🔒 Cara Menjalankan Proyek (Setup & Installation)

Demi alasan keamanan, API Key untuk layanan Gemini tidak disertakan di dalam repositori ini. Anda harus menggunakan API Key Anda sendiri.

1. Clone repositori ini:
   ```bash
   git clone [https://github.com/username_anda/nama_repo_anda.git](https://github.com/username_anda/nama_repo_anda.git)
