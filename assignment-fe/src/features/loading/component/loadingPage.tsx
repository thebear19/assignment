import React, {useEffect} from 'react';
import {useNavigate} from 'react-router-dom';

export function LoadingPage() {
    const isAccessAllowed = true;
    const navigate = useNavigate();

    useEffect(() => {
        const timer = setTimeout(() => {
            navigate('/login', {state: {isAccessAllowed: isAccessAllowed}});
        }, 3000); // Redirect after 3 seconds

        return () => clearTimeout(timer);
    }, [navigate, isAccessAllowed]);

    return (
        <div className="splash">
            <div className="loader"></div>
        </div>
    );
}
