---
title: Eshop ADPRO
emoji: 🛒
colorFrom: blue
colorTo: indigo
sdk: docker
app_port: 7860
pinned: false
---

# Reflection 1
* Secure Coding: Saya memeriksa apakah produk atau id produk yang ingin dihapus atau diedit tidak null, sebelum meelakukan operasi hapus atau edit produk. hal ini dilakukan untuk menghindari `NullPointerException`.
* Clean Code: Memberikan nama method, atribut, dan variabel yang jelas dan konsisten antar file agar mudah dibaca dan di-track bila ada error.
* Proses try & error:
  * Ada error yang terjadi ketika saya ingin menghapus produk, yaitu route yang dituju tidak ada saat tombol delete product ditekan. Ternyata itu terjadi karena saya belum inisiasi id produk saat ia dibuat, sehingga yang route yang seharusnya `product/delete/{id}` malah jadi `product/delete/` saja. Untuk penyelesaiannya, saat suatu produk dibuat, saya buatkan id nya menggunakan Integer secara manual. Agar ketika ia ingin dihapus, route nya ketemu.
  * Selanjutnya, karena operasi delete product diimplementasi sebelum operasi edit product, implementasi edit product yang saya lakukan jadi lebih lancar karena tidak mengulang kessalahan yang sama saat implementasi delete product.

---
# Reflection 2  
link deployment: https://farrelzzz-eshop-adpro.hf.space/product/list
## 1. Code Quality Issue(s) yang Diperbaiki
Berikut warnings yang saya terima ketika pertama kali berhasil menjalankan workflows PMD
<img width="1899" height="937" alt="Screenshot 2026-02-23 220034" src="https://github.com/user-attachments/assets/fe2a91c7-a96c-4f60-b697-e6eabc748996" />
* **pmd-code-scan**: saya menggunakan `github/codeql-action/upload-sarif@v3` untuk steps `Upload Sarif file` pada `pmd.yml`. Karena v3 akan segera deprecated, saya disarankan untuk mengganti ke versi yang lebih baru. Tetapi ketika  meggunakan v4 (yang lebih baru), muncul pesan `Unresolved action/workflow reference: "github/codeql-action/upload-sarif@v4"` dan workflows yang saya buat tidak bisa dijalankan. Akhirnya saya tetap pakai yang v3.
* **Unnecessary modifier `public` di method yang ada di interface**: saya hanya menghapus modifier `public` yang ada di awal method.
* **Unused `import org.springframework.web.bind.annotation.*` di Controller**: saya memakai dua controller, satu untuk hommepage dan satu lagi untuk product list page. Di homepage, import itu bisa diganti dnegan `annotation.GetMapping` saja karena hanya itu yang diperlukan, tapi saya tidak menggantinya karena rasanya sama saja. Sedangkan di product list ada banyak yang dibutuhkan, bukan GetMapping saja, maka dari itu saya tetap menggunakan `annotation.*`.
* **All methods are static di `EshopApplication.java`**: saya tidak melakukan  perubahan apapun untuk ini.

Berikut warnings yang tersisa dan test summary setelah perbaikan issues di atas
<img width="1885" height="729" alt="Screenshot 2026-02-23 223534" src="https://github.com/user-attachments/assets/77fbcd09-5acd-47b3-9df5-31d1af488f9e" />
[index.html](https://github.com/user-attachments/files/25494425/index.html) (ini html test summary)  
note: saya hanya melakukan functional test untuk homepage. Functional test untuk create, edit, dan delete product tidak dilakukan.  
  
---
# Reflection 3
## 1. SOLID Principles yang Diterapkan
* Single Responsibility Principle (SRP): SRP dilakukan dengan memisahkan ProductController dan CarController ke dua file yang tersendiri, sehingga setiap file hanya bertanggung jawab kepada satu controller.
* Dependency Inversion Principle (DIP): DIP dilakukan dengan menghilangkan ketergantungan CarController terhadap CarServiceImpl yang meruapakn concrete class. Sebagai gantinya, kita menggunakan interface dari CarServiceImpl, yaitu CarService.

## 2. Kelebihan Menerapkan SOLID Principles di Proyek Ini
Saat mengerjakan modul ini, sebelum menerapkan SOLID Principles, saya sempat mengalami error karena ada typo di file ProductController yang masih berisi dua controller.
Hal itu membuat saya bingung mencari mana controller yang salah.
Dari situ saya jadi merasakan sendiri salah satu kelebihan menerapkan SOLID Principles adalah debug jadi lebih mudah jika terjadi error.
Kelebihan lain yang saya rasa adalah jika kita melakukan penambahan kode, kita bisa memastikan bahwa kode yang sebelumnya sudah aman bisa tetap aman
dan tidak terganggu oleh kode yang baru ditambahkan.

## 3. Kekurangan Jika Tidak Menerapkan SOLID Principles di Proyek Ini 
Masih berhubungan dnegan poin 2, suatu blok kode masih melakukan terlalu banyak hal atau terlalu terikat dengan banyak hal.
Akibatnya, jika kita melakukan suatu penambahan atau perubahan, besar kemungkinan akan ada banyak hal lain yang terdampak dan mengalami error.
Selain itu, jika terjadi error, proses debug juga jadi lebih sulit. 
Tentunya hal itu tidak baik untuk maintainability proyek.

---
# Reflection 4
1. Flow TDD ini berguna bagi saya karena memaksa saya mendefinisika dahulu apa ekspektasi dari kode yang saya ingin buat sebelum logika aslinya ditulis, sehingga bisa meminimalkan kesalahan sejak awal.
Dengan urutan siklus Red-Green-Refactor, logika yang ditulis suda memiliki tujuan dan bisa langsung diuji.
Penggunaan mocking dalam tes tersebut juga menciptakan kode yang lebih rapi dan modular karena ketergantungan antar komponen terpisah dengan baik.

2. Tes ini sudah memenuhi keempat komponen FIRST principle:
Fast: cepat karena dijalankan menggunakan Mockito tanpa database asli.
Independent: tidak ada ketergantungan antar test karena data diatur ulang di @BeforeEach.
Repeatable: hasilnya sudah jelas (deterministik) dan bisa digunakan di manapun.
Self-Validating: test dapat menyimpulkan keberhasilan secara otomatis tanpa inspeksi manual.
Timely: test ditulis dahulu sebelum logic.
