import React from 'react';
import {Route, Routes} from 'react-router-dom';
import './assets/th-bank.css';
import ProtectedRoute from './component/protectedRoute';
import {LoadingPage} from "./features/loading/component/loadingPage";
import {LoginPage} from "./features/login/component/loginPage";
import {DashboardPage} from "./features/dashboard/component/dashboardPage";

export function App() {
    return (
        <Routes>
            <Route index element={<LoadingPage/>}/>
            <Route
                path="/login"
                element={
                    <ProtectedRoute redirectPath="/">
                        <LoginPage/>
                    </ProtectedRoute>
                }
            />
            <Route
                path="/main"
                element={
                    <ProtectedRoute redirectPath="/">
                        <DashboardPage/>
                    </ProtectedRoute>
                }
            />
        </Routes>
    );
}
