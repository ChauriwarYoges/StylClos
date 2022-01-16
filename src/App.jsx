import React from 'react';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";

import Home from './Home';
import Services from './Services';
import About from './About';
import ContactUs from './ContactUs';
import { Navigate, Route, Routes } from 'react-router';
import Navbar from './Navbar';

const App = () => {
    return (
        <>
            <Navbar />
            <Routes>
                <Route path="/" element={<Navigate to="/Home" />} />
                <Route path="/Home" element={<Home />} />
                <Route path="/Services" element={<Services />} />
                <Route path="/About" element={<About />} />
                <Route path="/ContactUs" element={<ContactUs />} />
            </Routes>
        </>
    );
};

export default App;