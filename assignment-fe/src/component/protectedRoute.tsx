import React, {JSX} from 'react';
import {Navigate, useLocation} from 'react-router-dom';

interface ProtectedRouteProps {
    redirectPath?: string;
    children: JSX.Element;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({redirectPath = '/', children}) => {
    const location = useLocation();
    const isAllowed = location.state?.isAccessAllowed ?? false;

    if (!isAllowed) {
        return <Navigate to={redirectPath} replace/>;
    }

    return children;
};

export default ProtectedRoute;