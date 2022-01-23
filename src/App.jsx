// import React from 'react';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Home from './Component/Home/Home';
import Services from './Component/Services/Services';
import About from './Component/About/About';
import ContactUs from './Component/Contact/ContactUs';
import { Navigate, Route, Routes } from 'react-router';
import NavbarComp from './Component/Navfoot/NavbarComp';
import Footer from './Component/Navfoot/Footer';
import Signup from './Component/Form/Signup';
import Login from './Component/Form/Login';
import './App.css';

const App = () => {
    return (
        <>
            <NavbarComp />
            <Routes>
                <Route path="/" element={<Navigate to="/Home" />} />
                <Route path="/Home" element={<Home />} />
                <Route path="/Services" element={<Services />} />
                <Route path="/About" element={<About />} />
                <Route path="/ContactUs" element={<ContactUs />} />
                <Route path='/Login' element={<Login />} />
                <Route path='/Signup' element={<Signup />} />
            </Routes>
            <Footer />
        </>
    );
};

export default App;