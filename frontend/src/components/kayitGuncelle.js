import Navbar from '../layout/navbar';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

function KayitGuncelle() {
    const [rows, setRows] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/kayitGoster')
            .then(response => {
                setRows(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error('Sunucudan veri alırken bir hata oluştu:', error);
            });
    }, []);

    const handleInputChange = (e, index, field) => {
        const { value } = e.target;
        const newRows = [...rows];
        newRows[index][field] = value;
        setRows(newRows);
    };

    const handleUpdateRecord = (index) => {
        const updatedData = rows[index];
        console.log(updatedData);
        axios.put(`http://localhost:8080/kayitGuncelle`, updatedData)
            .then(response => {
                window.alert("Kayıt başarılı bir şekilde güncellendi.");
            })
            .catch(error => {
                window.alert("Güncelleme yaparken bir sorun oldu tekrar deneyin");
            });
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
                        <th scope='col'></th>
                    </tr>
                </thead>
                <tbody>
                    {rows.map((row, index) => (
                        <tr key={row.dosyaId}>
                            <th scope="row">{row.dosyaİd}</th>
                            <td><input type="text" value={row.hastaAd} onChange={(e) => handleInputChange(e, index, 'hastaAd')} /></td>
                            <td><input type="text" value={row.hastaSoyad} onChange={(e) => handleInputChange(e, index, 'hastaSoyad')} /></td>
                            <td><input type="text" value={row.hastaTC} onChange={(e) => handleInputChange(e, index, 'hastaTC')} /></td>
                            <td><input type="text" value={row.hastaTaniBaslik} onChange={(e) => handleInputChange(e, index, 'hastaTaniBaslik')} /></td>
                            <td><input type="text" value={row.hastaTaniDetay} onChange={(e) => handleInputChange(e, index, 'hastaTaniDetay')} /></td>
                            <td><input type="text" value={row.kayitTarih} onChange={(e) => handleInputChange(e, index, 'kayitTarih')} /></td>
                            <td>
                                <button type="button" className="btn btn-succes" onClick={() => handleUpdateRecord(index)}>Güncelle</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}

export default KayitGuncelle;
