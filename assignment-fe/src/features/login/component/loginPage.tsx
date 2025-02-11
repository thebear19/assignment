import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {login} from '../service/authService';

export function LoginPage() {
    const isAccessAllowed = true;
    const navigate = useNavigate();
    const name = 'User_000018b0e1a211ef95a30242ac180002';
    const [pin, setPin] = useState('');
    const [error, setError] = useState(false);

    const handleButtonClick = (value: string) => {
        setPin(value === 'del' ? pin.slice(0, -1) : pin + value);
    };

    useEffect(() => {
        if (pin.length === 6) {
            login(name, pin)
                .then(data => {
                    if (data.id && data.token) {
                        navigate('/main', {state: {credentials: data, name, isAccessAllowed: isAccessAllowed}});
                    } else {
                        setError(true);
                        setPin('');
                    }
                })
                .catch(error => {
                    setError(true);
                    setPin('');
                });
        }
    }, [pin, navigate, isAccessAllowed]);

    return (
        <main className="container container--pin-type">
            <div className="pin">
                <div className="pin__top">
                    <span className="pin__photo"><img src="https://dummyimage.com/200x200/999/fff" alt=""/></span>
                    <h1 className="pin__name">Interview User</h1>
                    <p className="pin__dsc" style={{display: error ? "contents" : "none"}}>
                        Invalid PIN Code.<br/>You have 3 attempt left.
                    </p>
                    <div className="pin__dots">
                        {[...Array(6)].map((_, i) => (
                            <span key={i} className={`pin__dot ${pin.length > i ? 'is-filled' : ''}`}></span>
                        ))}
                    </div>
                </div>
                <div className="pin__btm">
                    <a href="#" className="pin__login">Login with ID / Password </a>
                    <span className="pin__kb">Powered by TestLab</span>
                    <div className="pin__keys">
                        {[1, 2, 3, 4, 5, 6, 7, 8, 9, 0].map((num) =>
                            num === 0 ? (
                                <>
                                    <span key="space" className="pin__key pin__key--space"></span>
                                    <button
                                        key={num}
                                        type="button"
                                        className="pin__key"
                                        onClick={() => handleButtonClick(num.toString())}
                                    >
                                        {num}
                                    </button>
                                </>
                            ) : (
                                <button
                                    key={num}
                                    type="button"
                                    className="pin__key"
                                    onClick={() => handleButtonClick(num.toString())}
                                >
                                    {num}
                                </button>
                            )
                        )}
                        <button
                            type="button"
                            className="pin__key pin__key--del"
                            onClick={() => handleButtonClick('del')}
                        >
                            <span className="blind">Delete</span>
                        </button>
                    </div>
                </div>
            </div>
        </main>
    );
}
