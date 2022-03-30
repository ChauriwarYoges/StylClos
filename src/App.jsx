// import React from 'react';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Home from './Component/Home/Home';
import Womens from './Component/Womens/Womens';
import Mens from './Component/Mens/Mens';
import ContactUs from './Component/Contact/ContactUs';
import ProductDetailedPage from "./Component/Cards/ProductDetailedPage";
import { Navigate, Route, Routes } from 'react-router';
import NavbarComp from './Component/Navfoot/NavbarComp';
import Footer from './Component/Navfoot/Footer';
import Signup from './Component/Form/Signup';
import Login from './Component/Form/Login';
import LoginSeller from './Component/Seller/Login';
import SignupSeller from './Component/Seller/Signup';
import SellerHome from './Component/Seller/Home';
import './App.css';


const App = () => {
    return (
        <>
            <NavbarComp />
            <Routes>
                <Route path="/" element={<Navigate to="/Home" />} />
                <Route path="/Home" element={<Home />} />
                <Route path="/Womens" element={<Womens />} />
                <Route path="/Mens" element={<Mens />} />
                <Route path="/ContactUs" element={<ContactUs />} />
                <Route path='/Login' element={<Login />} />
                <Route path='/Signup' element={<Signup />} />
                <Route path='/ProductDetailedPage' element={<ProductDetailedPage />} />
                <Route path='/seller' element={<LoginSeller />} />
                <Route path="/seller/login" element={<LoginSeller />} />
                <Route path="/seller/signup" element={<SignupSeller />} />
                <Route path="/seller/home" element={<SellerHome />} />
            </Routes>
            <Footer />
        </>
    );
};

export default App;