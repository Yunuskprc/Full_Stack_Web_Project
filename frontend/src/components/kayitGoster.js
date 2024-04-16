import Navbar from '../layout/navbar';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function KayitGoster() {
    const [data, setData] = useState([]);
    const [aranacakTur, setAranacakTur] = useState('');
    const [aramaKelimesi, setAramaKelimesi] = useState('');
    const [inputType, setInputType] = useState('text'); 
    const navigate = useNavigate();

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

    const handleSearch = (e) => {
        e.preventDefault();
        const aranacakTur = document.getElementById('aranacakTur').value;
        const aramaKelimesi = document.getElementById('aramaKelimesi').value;

        if (!aranacakTur) {
            window.alert('Lütfen aranacak bir tür seçin');
            window.location.reload();
            return;
        }

        if (!aramaKelimesi) {
            window.location.reload();
            return;
        }

        console.log(aramaKelimesi)
        axios.get('http://localhost:8080/arama', {
            params: {
                aranacakTur: aranacakTur,
                aramaKelimesi: aramaKelimesi
            }
        })
            .then(response => {
                console.log(response.data);
                setData(response.data);
            })
            .catch(error => {
                if (error.response && error.response.data) {
                    window.alert(error.response.data);
                } else {
                    window.alert('Bilinmeyen bir hata oluştu.');
                }
                console.error(error);
            });
    };

  
    useEffect(() => {
        if (aranacakTur === '6') {
            setInputType('date'); // Tarih için input tipini 'date' olarak ayarla
        } else {
            setInputType('text'); // Diğer tüm türler için input tipini 'text' olarak ayarla
        }
    }, [aranacakTur]);

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
            <div className="navbar navbar-expand-lg navbar-light bg-light">
                <form className="d-flex my-2 my-lg-0" style={{ marginLeft: '600px' }} onSubmit={handleSearch}>
                    <select className="form-control me-2" id="aranacakTur" style={{ width: '400px' }}
                        value={aranacakTur}
                        onChange={e => setAranacakTur(e.target.value)}
                    >
                        <option value="" selected>
                            Arama yapılacak değeri seçin
                        </option>
                        <option value="1">Hasta Ad</option>
                        <option value="2">Hasta Soyad</option>
                        <option value="3">Tc Kimlik No</option>
                        <option value="4">Hasta Tanı Başlık</option>
                        <option value="5">Hasta Tanı Detay</option>
                        <option value="6">Tarih</option>
                    </select>
                    <input
                        className="form-control me-2"
                        type={inputType} // Dinamik olarak ayarlanmış input tipi
                        placeholder={inputType === 'date' ? 'Tarih girin' : 'Aramak istediğiniz değeri girin'}
                        aria-label="Search"
                        id="aramaKelimesi"
                        value={aramaKelimesi}
                        onChange={e => setAramaKelimesi(e.target.value)}
                    />
                    <button
                        className="btn btn-outline-success"
                        type="submit"
                    >
                        Ara
                    </button>
                </form>
            </div>

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
                        <th scope='col'>Resim</th>
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
