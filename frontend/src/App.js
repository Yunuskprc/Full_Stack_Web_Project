import React from 'react';
import Login from './pages/login';
import Anasayfa from './pages/anasayfa';
import Register from './pages/register';
import KayitGoster from './components/kayitGoster';
import KayitEkle from './components/kayitekle';
import KayitGuncelle from './components/kayitGuncelle';
import KayitSil from './components/kayitsil';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css"
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import CookieConsent from './components/CookieConsent';

function App() {
    return (
        <div className="App">
            <Router>
                <CookieConsent /> {}
                <Routes>
                    <Route exact path="/" element={<Login />} />
                    <Route exact path="/Anasayfa" element={<Anasayfa />} />
                    <Route exact path="/Register" element={<Register />} />
                    <Route exact path="/kayitEkle" element={<KayitEkle />} />
                    <Route exact path="/kayitGoruntule" element={<KayitGoster />} />
                    <Route exact path="/kayitGuncelle" element={<KayitGuncelle />} />
                    <Route exact path="/kayitSil" element={<KayitSil />} />
                </Routes>
            </Router>
        </div>
    );
}

export default App;
