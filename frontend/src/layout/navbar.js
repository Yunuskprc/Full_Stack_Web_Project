import React from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';


function Navbar() {

  
  const handleClick = () => {
    axios.get('http://localhost:8080/logOut', {
        withCredentials: true 
      })
      .then(response => {

          window.alert("Çıkış işlemi başarılı bir şekilde gerçekleşti");
          Cookies.remove('JSESSIONID');
          window.location.href = "/";

      }).catch(error => {
        const errorMessage = error.response?.data || "Çıkış yapılamıyor, lütfen daha sonra tekrar deneyin";
        window.alert(errorMessage);
      });
  }

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <a className="navbar-brand" href="/Anasayfa">Kayıtlar</a>
      <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div className="navbar-nav">
          <a className="nav-item nav-link active" href="/kayitGoruntule">Kayıt Görüntüle</a>
          <a className="nav-item nav-link" href="/kayitGuncelle">Kayıt Güncelle</a>
          <a className="nav-item nav-link" href="/kayitEkle">Kayıt Ekle</a>
          <a className="nav-item nav-link" href="/kayitSil">Kayıt Sil</a>
        </div>
      </div>
      <div className="btn-group ml-auto">
        <button type="button" className="btn btn-danger" onClick={handleClick}>Çıkış Yap</button>
      </div>
    </nav>
  );
}

export default Navbar;
