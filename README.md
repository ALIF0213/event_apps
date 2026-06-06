# 📱 Smart Event Explorer (with Gemini AI)

A modern Kotlin-based Android application to intelligently explore, save, and summarize tech event information. Built adhering to Clean Architecture (MVVM) principles and Material Design interface standards to provide a seamless and premium user experience.
*(Aplikasi Android modern berbasis Kotlin untuk menjelajahi, menyimpan, dan merangkum informasi acara teknologi secara cerdas. Dibangun dengan mematuhi prinsip Clean Architecture (MVVM) dan standar antarmuka Material Design untuk memberikan pengalaman pengguna yang mulus dan premium.)*

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Room Database](https://img.shields.io/badge/Room_Database-SQLite-blue?style=for-the-badge)
![Gemini AI](https://img.shields.io/badge/Gemini_AI-Google-orange?style=for-the-badge)

## ✨ Key Features (*Fitur Unggulan*)

* 🧠 **Smart AI Summarizer (Gemini 3.5 Flash Integration)**
  Users no longer need to read lengthy event descriptions. With just one tap, the system calls Google Generative AI to provide 3 concise summary points (About the Event, Target Audience, and Key Benefits) in real-time.
  *(Pengguna tidak perlu membaca deskripsi acara yang terlalu panjang. Hanya dengan satu ketukan, sistem memanggil Google Generative AI untuk memberikan 3 poin ringkasan padat—Tentang Acara, Target Peserta, dan Manfaat—secara real-time.)*

* 💾 **Offline-First Favorites (Room Database)**
  Robust local storage architecture implementation. Users can save their favorite events and access them anytime in the Favorite tab, even entirely without an internet connection.
  *(Implementasi arsitektur penyimpanan lokal yang kokoh. Pengguna dapat menyimpan acara favorit mereka dan mengaksesnya kapan saja di tab Favorite, bahkan tanpa koneksi internet sama sekali.)*

* 🚀 **Modern & Fluid UI/UX**
  * **Shimmer Effect:** Loading transitions using Facebook Shimmer Skeleton, replacing traditional spinners. *(Transisi loading menggunakan Facebook Shimmer Skeleton, menggantikan spinner tradisional.)*
  * **Swipe to Refresh:** Seamless data resynchronization with a pull-down gesture. *(Sinkronisasi ulang data secara mulus dengan gerakan tarik-ke-bawah.)*
  * **Parallax Scrolling:** Interactive poster image animation when users scroll through the detail page. *(Animasi gambar poster yang interaktif saat pengguna menggulir halaman detail.)*
  * **Material Elevation:** Drop-shadow card design providing visual depth and clear hierarchy. *(Desain kartu timbul yang memberikan kedalaman visual dan hierarki yang jelas.)*

## 🛠️ Tech Stack & Architecture (*Teknologi & Arsitektur*)

This application utilizes cutting-edge Android development technologies:
*(Aplikasi ini menggunakan teknologi pengembangan Android mutakhir:)*
* **Architecture:** MVVM (Model-View-ViewModel) for clean logic and UI separation. *(Pemisan logic dan UI yang bersih.)*
* **Networking:** Retrofit2 & OkHttp3 (RESTful API).
* **Asynchronous:** Kotlin Coroutines & `lifecycleScope` for lightweight background operations. *(Operasi background yang ringan.)*
* **Local Storage:** Room Persistence Library (SQLite Abstraction).
* **AI Integration:** `com.google.ai.client.generativeai` (Gemini API).
* **Image Loading:** Glide.

## 📸 Screenshots (*Cuplikan Layar*)
*(Add your application screenshots here / Tambahkan gambar cuplikan layar aplikasi Anda di sini)*

| Home Screen | AI Summarizer | Offline Favorites |
| :---: | :---: | :---: |
| <img src="Home Screen.jpeg" width="200"/> | <img src="Ai Summarize.jpeg" width="200"/> | <img src="Offline Favorites.jpeg" width="200"/> |

## 🔒 Setup & Installation (*Cara Menjalankan Proyek*)

For security reasons, the API Key for the Gemini service is not included in this repository. You must use your own API Key.
*(Demi alasan keamanan, API Key untuk layanan Gemini tidak disertakan di dalam repositori ini. Anda harus menggunakan API Key Anda sendiri.)*

1. Clone this repository (*Clone repositori ini*):
```bash
   git clone [https://github.com/ALIF0213/aplikasi_event.git](https://github.com/ALIF0213/aplikasi_event.git)
