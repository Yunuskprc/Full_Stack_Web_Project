import Navbar from '../layout/navbar';
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function KayitSil() {
    const [data, setData] = useState([]);
    const navigate = useNavigate();
  
    useEffect(() => {
        axios.get('http://localhost:8080/kayitGoster')
            .then(response => {
                setData(response.data);
            })
            .catch(error => {
                console.error('Sunucudan veri alırken bir hata oluştu:', error);
            });
    }, []);

    const handleDeleteRecord = (id,dosyaİd) => {
        console.log(id,dosyaİd)
        axios.delete(`http://localhost:8080/kayitSil`,{params:{id,dosyaİd}})
            .then(response => {
                setData(data.filter(row => row.dosyaId !== id));
                window.location.reload();
            })
            .catch(error => {
                console.error('Sunucudan veri alırken bir hata oluştu:', error);
            });
    };

    useEffect(() => {
        axios.get('http://localhost:8080/isLoggin', { withCredentials: true })
            .then(response => {
                
            })
            .catch(error => {
                console.error('Kullanıcı durumu kontrol edilirken bir hata oluştu:', error);
                if (error.response && error.response.status === 404 && error.response.data === "Lütfen giriş yapınız") {
                    window.alert("Lütfen giriş yapınız");
                }
                navigate('/');
            });
    }, [navigate]);

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
                        <th scope="col">Resim</th>
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
                                <button type="button" className="btn btn-danger" onClick={() => handleDeleteRecord(row.id,row.dosyaİd)}>Kaydı Sil</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}

export default KayitSil;
