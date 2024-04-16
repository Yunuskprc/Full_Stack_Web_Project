import Navbar from '../layout/navbar';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function KayitGoster() {
    const [data, setData] = useState([]);
    const [imageUrl, setImageUrl] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/kayitGoster')
            .then(response => {
                setData(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error('Sunucudan veri alırken bir hata oluştu:', error);
            });
    }, []);

    const handleShowImage = (url) => {
      const fullUrl = `http://localhost:8080/image/${url}`;
      console.log(fullUrl);
      window.open(fullUrl, '_blank');
  };

    return (
        <>
            <Navbar />
            <table className="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Ad</th>
                        <th scope="col">Soyad</th>
                        <th scope="col">TC Kimlik No</th>
                        <th scope="col">Tanı Başlık</th>
                        <th scope="col">Tanı Detay</th>
                        <th scope="col">Kayıt Tarih</th>
                        <th scope='col'>
                            Resim
                        </th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((row) => (
                        <tr key={row.dosyaId}>
                            <th scope="row">{row.dosyaİd}</th>
                            <td>{row.hastaAd}</td>
                            <td>{row.hastaSoyad}</td>
                            <td>{row.hastaTC}</td>
                            <td>{row.hastaTaniBaslik}</td>
                            <td>{row.hastaTaniDetay}</td>
                            <td>{row.kayitTarih}</td>
                            <td>
                                <button type="button" className="btn btn-light" onClick={() => handleShowImage(row.resimURL)}>Resim Göster</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}

export default KayitGoster;
