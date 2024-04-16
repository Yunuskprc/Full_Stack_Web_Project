import React, { useState, useEffect } from 'react';

const CookieConsent = () => {
    const [consent, setConsent] = useState(false);
    
    useEffect(() => {
        const consentGiven = localStorage.getItem('cookieConsent');
        if (consentGiven === 'true') {
            setConsent(true);
        }
    }, []);
    
    const handleConsent = () => {
        localStorage.setItem('cookieConsent', 'true');
        setConsent(true);
    };

    if (!consent) {
        return (
            <div style={{ position: 'fixed', bottom: 0, backgroundColor: 'white', padding: '10px', width: '100%' }}>
                <p>Bu site çerezleri kullanıyor. Devam etmek için izin verin.</p>
                <button onClick={handleConsent}>İzin Ver</button>
            </div>
        );
    }
    return null;
};

export default CookieConsent;
