import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import {App} from './App';
import reportWebVitals from './reportWebVitals';
import {MemoryRouter} from "react-router-dom";

const rootDom = document.getElementById('root');
rootDom.classList.add("wrap");
const root = ReactDOM.createRoot(rootDom);
root.render(
    <MemoryRouter>
        <App />
    </MemoryRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
