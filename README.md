---
title: Eshop ADPRO
emoji: 🛒
colorFrom: blue
colorTo: indigo
sdk: docker
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
