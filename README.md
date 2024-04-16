# Ful_Stack_Web_Project

Bu proje, React frontend ve Spring Boot backend kullanılarak oluşturulmuş bir full stack web projesidir. Bu proje bir hastane kayıt/raporlama uygulamasıdır. Projenin amacı yetkili kullanıcıların hastaneye müraacat
eden hastaların bilgilerini sisteme ekleyerek kayıtları güncelleme,silme ve kayıtları sıralama işlemlerini yapabilmesi için basit bir kullanım sağlamaktır. Yetkili kullanıcılar sisteme giriş yaparak kayıt ekleme,
güncelleme, arama ve silme işlemlerini gerçekleştirebilir. 2 tip yetkili kullanıcı bulunmaktadır. Birinci tip yetkili kullanıcı tüm işlemleri gerçekleştirebilirken ikinci tip yetkili kullanıcı kayıt ekleme, sıralama,
arama ve güncelleme işlemlerini yapabilir. 




## İçindekiler

- [Gereksinimler](#gereksinimler)
- [Kurulum](#kurulum)
- [Kullanım](#kullanım)
- [Proje Yapısı](#proje-yapısı)

## Gereksinimler

- **Java** 11 veya üstü
- **Node.js** 14 veya üstü
- **NPM**
- **Spring Boot** 2.5 veya üstü
- **MySQL**

## Kurulum

Projeyi kurmak ve çalıştırmak için aşağıdaki adımları takip edin:

1. **Depoyu klonlayın:**
    ```bash
    git clone https://github.com/Yunuskprc/Full_Stack_Web_Project.git
    cd proje-adi
    ```

2. **Backend için bağımlılıkları yükleyin:**
    ```bash
    cd backend
    mvn install
    ```

3. **Frontend için bağımlılıkları yükleyin:**
    ```bash
    cd ../frontend
    npm install
    ```

4. **Backend'i çalıştırın:**
    ```bash
    cd backend
    mvn spring-boot:run
    ```

5. **Frontend'i çalıştırın:**
    ```bash
    cd ../frontend
    npm start
    ```

## Kullanım

- Backend çalıştığında, API'lere erişim için `http://localhost:8080` adresini kullanabilirsiniz.
- Frontend çalıştığında, kullanıcı arayüzüne erişim için `http://localhost:3000` adresini kullanabilirsiniz.
  

## Proje Yapısı

- `backend/`: Spring Boot backend uygulaması
- `backend/src/main/java/com/example/ProjeStaj/modal/`: JPA modal classları bulunur. Bunlar User, HospitalRecord, Session.
- `backend/src/main/java/com/example/ProjeStaj/repository/` : Modal classların reposity interface'leri bulunur. Bunlar UserRepository, RecordRepository, SessionRepository.
- `backend/src/main/java/com/example/ProjeStaj/controller/` : Controller enppointleri bulunur. Bunlar LoginController, RecordController, RegisterController, SessionController.  
 **LoginController da** 2 tane endpoit bulunur. Bu endpointler login ve logout işlemlerini gerçekleştirir.  
 **RegisterController da** post endpoit bulunur. Ayrıca Random olarak 7 basamakalı hospitalId üreten generateRandomHospitalId metodu da bu Controller da bulunur. Üretilen bu değerler tekildir.  
 **RecordController da** kayıtları listeleyen, arama yapan, ekleme yapan, güncelleme yapan ve silme işlemini yapan endpointler bulunur.  
 **SessionController da** isLoggin endpoiti bulunur. Bu endpoint eğer session başlatılmamışsa kullanıcıların url adreslerine erişimini geriye dönderdiği ResponseEntity status değeri ile engeller.
    
- `backend/src/main/java/com/example/ProjeStaj/services/` : Servis classlarım burada bulunur. Bunlar SearchService ve ImageService.
  **ImageService** seçilen resmi static dosyası içindeki image klasörüne resim adının önüne anlık tarih saat verisi ekleyerek kopyalar. Değişen resmin adı ise veritabanında ilgili alanda saklanır.
  **SearchService**  farklı yerlerde search işlemi yapmak istenirse birbirinden ayrılmış repository çağrıları bulunur ve böylece kod tekrarının önüne geçilmiş olur.

- `backend/src/main/java/com/example/ProjeStaj/config/` : Config classlarım burada bulunur. Bunlar CookieConfig ve WebConfig.
  **CookieConfig** config classında cookieSerializer metodu bir CookieSerializer bean'i oluşturur. Bu bean, oturum çerezlerinin nasıl oluşturulup yönetileceğini tanımlar.
  **WebConfig** config clası WebMvcConfigurer interface ini implemente ederek HTTP isteklerini yönetmek için CORS ayarlarını yapılandırır.


  - `frontend/`: React frontend uygulaması
  - `/frontend/src/components/` : React componentleri burada bulunur. Bunlar CookieConsent, kayitekle, kayitGoster, kayitGuncelle ve kayitsil.
**CookieConsent** componenti kullanıcının çerez kullanımı için tarayıcı izini ister ve app.js de çağrılır. Çerez kullanımı session yönetimi için bu projede çok önemlidir.
- `/frontend/src/layout/` : Navbar.js classı burada bulunur.
- - `/frontend/src/pages/` : login.js, register.js ve anasayfa.js clasları burada bulunur.

Projede 2 farklı port kullanımı olduğu için CORS ayarlarını düzenlememiz gerekir. Kullanıcı yönetimi (admin/user) sağlamak için session kullanıldı ve session kullanımında sorun çıkmaması için tarayıcıdan 
site çerez kullanımı için izin almak gerekiyor. Kullanıcı yönetimi ile işlem kısıtı sağlanıyor böylece istenen kullanıcının erişimini kısıtlayabiliriz. Login yaptıktan sonra session başlatılır ve session
modal a ilgili setler yapıldıktan sonra veritabanına kayıt gerçekleştirilir. Logout yapınca ise ilgili session reposu silinir. Bunun amacı session başladıktan sonra giriş yapan kullanıcının type ve id değerlerine
kolayca erişmektir. Bu şekilde session yönetimi daha hızlı bir şekilde gerçekleştirilir. Eğer session başlatılmamışsa (login yapılmamışsa) kullanıcılar diğer sayfalara erişemez bu da site ve server için güvenlik
sağlamaktadır. Backend de kullanılan servisler sayesinde başka metotlarda aynı işlemleri gerçekleştirmek istenirse kod tekrarın önüne geçilmiş olacak ve daha hızlı iletişim kurulacaktır.

Bu proje tek başıma yaptığım ilk full-stack projesidir  ve daha önce react, spring teknolojilerini kullanmamıştım. 
İlgi alanım web-backend olmasına rağmen boostrap kullanarak basit bir UI ile yaptığım ilk frontend projemdir.
Projeyi yapma amacım farklı teknolojilere daha hızlı uyum sağlamaktır.
Proje Resimleri altta bulunmaktadır.


# Proje Fotoğrafları
![LoginPage](https://github.com/Yunuskprc/Full_Stack_Web_Project/assets/91240806/494eff13-5b09-4702-97b5-9321a9cdf91d)
![RegisterPage](https://github.com/Yunuskprc/Full_Stack_Web_Project/assets/91240806/d7b69407-6e9b-46e7-8aaf-83c35d360224)
![MainPage](https://github.com/Yunuskprc/Full_Stack_Web_Project/assets/91240806/24e534ea-3eca-40ab-a739-d7911381fc83)
![RecordShow](https://github.com/Yunuskprc/Full_Stack_Web_Project/assets/91240806/5207976a-8199-4988-b870-caad0b72b620)
![RecordAdd](https://github.com/Yunuskprc/Full_Stack_Web_Project/assets/91240806/d3b90b52-5cb0-4263-b94a-780d5e2b44df)
![RecordUpdate](https://github.com/Yunuskprc/Full_Stack_Web_Project/assets/91240806/dda17285-4c98-4018-9177-0352977cdb6b)
![RecordDelete](https://github.com/Yunuskprc/Full_Stack_Web_Project/assets/91240806/60613ce6-b7b2-4e2b-b124-59c5ebae433a)


