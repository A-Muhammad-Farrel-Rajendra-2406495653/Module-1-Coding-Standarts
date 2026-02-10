# Reflection 1
* Secure Coding: Saya memeriksa apakah produk atau id produk yang ingin dihapus atau diedit tidak null, sebelum meelakukan operasi hapus atau edit produk. hal ini dilakukan untuk menghindari `NullPointerException`.
* Clean Code: Memberikan nama method, atribut, dan variabel yang jelas dan konsisten antar file agar mudah dibaca dan di-track bila ada error.
* Proses try & error:
  * Ada error yang terjadi ketika saya ingin menghapus produk, yaitu route yang dituju tidak ada saat tombol delete product ditekan. Ternyata itu terjadi karena saya belum inisiasi id produk saat ia dibuat, sehingga yang route yang seharusnya `product/delete/{id}` malah jadi `product/delete/` saja. Untuk penyelesaiannya, saat suatu produk dibuat, saya buatkan id nya menggunakan Integer secara manual. Agar ketika ia ingin dihapus, route nya ketemu.
  * Selanjutnya, karena operasi delete product diimplementasi sebelum operasi edit product, implementasi edit product yang saya lakukan jadi lebih lancar karena tidak mengulang kessalahan yang sama saat implementasi delete product.   
