import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Navbar from '../layout/navbar';

function KayitEkle() {
    const navigate = useNavigate();

    const [record, setRecord] = useState({
        hastaAd: "",
        hastaSoyad: "",
        hastaTC: "",
        hastaTaniBaslik: "",
        hastaTaniDetay: "",
        resimURL: null
    });

    const { hastaAd, hastaSoyad, hastaTC, hastaTaniBaslik, hastaTaniDetay, resimURL } = record;

    const onInputChange = (e) => {
        setRecord({ ...record, [e.target.name]: e.target.value });
    };

    const onFileChange = (e) => {
        const file = e.target.files[0];
        setRecord({ ...record, resimURL: file });
    };

    const onSubmit = async (e) => {
        e.preventDefault();

        const formData = new FormData();
        formData.append('hastaAd', hastaAd);
        formData.append('hastaSoyad', hastaSoyad);
        formData.append('hastaTC', hastaTC);
        formData.append('hastaTaniBaslik', hastaTaniBaslik);
        formData.append('hastaTaniDetay', hastaTaniDetay);
        formData.append('resimURL', resimURL);

        try {
            const response = await axios.post("http://localhost:8080/kayitEkle", formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                withCredentials: true // İsteğe kimlik doğrulama bilgilerini ekleyin
            });

            navigate("/Anasayfa");
            window.alert("Kayıt başarılı bir şekilde eklendi");

        } catch (error) {
            window.alert("Bir hata oluştu: " + error.message);
            console.error('Veri gönderilirken bir hata oluştu:', error);
        }
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
            <div className='container'>
                <div className='row'>
                    <div className='col-md-6 offset-md-3 border rounded p-4 mt-2 shadow'>
                        <h2 className='text-center m-4'>Hasta Kayıt Ekleme</h2>
                        <form onSubmit={onSubmit} encType="multipart/form-data">
                            <div className='mb-3'>
                                <label className='form-label' htmlFor='userName'>Hasta Adı:</label>
                                <input type="text" className='form-control' name='hastaAd' value={hastaAd} onChange={onInputChange} />
                            </div>

                            <div className='mb-3'>
                                <label className='form-label' htmlFor='userName'>Hasta Soyadı:</label>
                                <input type="text" className='form-control' name='hastaSoyad' value={hastaSoyad} onChange={onInputChange} />
                            </div>

                            <div className='mb-3'>
                                <label className='form-label' htmlFor='userName'>Hasta TC:</label>
                                <input type="text" className='form-control' name='hastaTC' value={hastaTC} onChange={onInputChange} />
                            </div>

                            <div className='mb-3'>
                                <label className='form-label' htmlFor='userName'>Tanı Başlık:</label>
                                <input type="text" className='form-control' name='hastaTaniBaslik' value={hastaTaniBaslik} onChange={onInputChange} />
                            </div>

                            <div className='mb-3'>
                                <label className='form-label' htmlFor='userName'>Tanı Detay:</label>
                                <input type="text" className='form-control' name='hastaTaniDetay' value={hastaTaniDetay} onChange={onInputChange} />
                            </div>

                            <div className="mb-3">
                                <div className="custom-file">
                                    <input type="file" className="form-label" name='resimURL' id="resimURL" placeholder='Resim Ekle' accept=".png, .jpg, .jpeg, .gif" onChange={onFileChange} />
                                </div>
                            </div>

                            <button type='submit' className='btn btn-outline-primary'>Kaydet</button>
                            <Link to={"/Anasayfa"} className='btn btn-outline-danger mx-2'>Vazgeç</Link>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
}

export default KayitEkle;
