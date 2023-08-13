-- TEST JASAMEDIKA --
Spring Boot App
Yang Perlu Disiapkan
- JDK20
- PostgreSql
- Postman, Thunder Client, atau aplikasi sejenis

Instalasi
- Clone Repository ini
- Buka Projek di IDE yang Mendukung Java & Spring Boot
- Run Aplikasi ini di terminal dengan perintah : mvn spring-boot:run , atau dengan action button RUN pada IDE
- Dalam file **/jasamedika/src/main/resources/application.yaml,ada konfigurasi untuk koneksi postgre. Harap disesuaikan dengan koneksi postgre anda
- Dalam file **/jasamedika/src/main/resources/application.yaml,ada konfigurasi untuk menyimpan photo yang di upload -> upload :
            file : *alamat direktori
    silahkan sesuaikan dengan direktori mana yang menjadi tujuannya
- Untuk Dokumentasi API,anda bisa mengakses : http://localhost:8081/swagger-ui/index.html
