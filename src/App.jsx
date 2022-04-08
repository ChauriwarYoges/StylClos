// import React from 'react';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { Navigate, Route, Routes } from 'react-router';
import NavbarComp from './Component/Navfoot/NavbarComp';
import Footer from './Component/Navfoot/Footer';
import './App.css';

// AUTHS IMPORTS
import AdminProtectedRoute from "./Component/ProtectedRoutes/AdminProtectedRoute";
import SellerProtectedRoute from "./Component/ProtectedRoutes/SellerProtectedRoute";
import CustomerProtectedRoute from "./Component/ProtectedRoutes/CustomerProtectedRoute";

import Unauthorized from "./Component/Unauthorized";

import Profile from "./Component/Profile/Profile";

// ANONYMOUS IMPORTS
import Signup from './Component/Form/Signup';
import Login from './Component/Form/Login';
import Home from './Component/Home/Home';
import Womens from './Component/Womens/Womens';
import Mens from './Component/Mens/Mens';
import ContactUs from './Component/Contact/ContactUs';
import ProductDetailedPage from "./Component/Cards/ProductDetailedPage";

// CUSTOMER IMPORTS
import Cart from "./Component/Customer/Cart";
import Order from "./Component/Customer/Order";
import Logout from './Component/Form/Logout';

// SELLER IMPORTS
import LoginSeller from './Component/Seller/Login';
import SignupSeller from './Component/Seller/Signup';
import SellerHome from './Component/Seller/Home';
import Addproduct from "./Component/Seller/Addproduct";
import Editproduct from "./Component/Seller/Editproduct";

// ADMIN IMPORTS
import AdminHome from './Component/Admin/Home';
import AdminLogin from './Component/Admin/Login';


const App = () => {
    return (
        <>
            <NavbarComp />
            <Routes>
                { /* AnoynmousUser Routes*/}
                <Route path="/" element={<Navigate to="/Home" />} />
                <Route path="/Home" element={<Home />} />
                <Route path="/Womens" element={<Womens />} />
                <Route path="/Mens" element={<Mens />} />
                <Route path="/ContactUs" element={<ContactUs />} />
                <Route path='/Login' element={<Login />} />
                <Route path='/Signup' element={<Signup />} />
                <Route path='/ProductDetailedPage/:id' element={<ProductDetailedPage />} />
                <Route path="/unauthorized" element={<Unauthorized />} />

                { /* CUSTOMER ROUTES */}
                { /* CUSTOMER PROTECTED PATHS */}
                <Route path="/cart" element= { <CustomerProtectedRoute Cmp={Cart} /> } />
                <Route path="/order" element = { <CustomerProtectedRoute Cmp={Order} /> } />

                
                { /* Seller Routes */}
                <Route path="/seller/login" element={<LoginSeller />} />
                <Route path="/seller/signup" element={<SignupSeller />} />

                { /* SELLER PROTECTED PATHS */}
                <Route path="/seller/home" element={<SellerProtectedRoute Cmp={SellerHome} />} />
                <Route path='/seller' element={<SellerProtectedRoute Cmp={SellerHome} />} />
                <Route path="/seller/addproduct" element={<SellerProtectedRoute Cmp={Addproduct} />} />
                <Route path="/seller/productedit/:id" element={<SellerProtectedRoute Cmp={Editproduct} />} />
                <Route path="/seller/delete/:id" element={<SellerProtectedRoute Cmp={SellerHome} />} />

                { /* Admin Routes */}
                <Route path="/admin/login" element={<AdminLogin />} />
                <Route path='/admin' element={<AdminLogin />} />

                { /* ADMIN PROTECTED PATHS */}
                <Route path='/admin/register' element={<AdminProtectedRoute Cmp={AdminHome} />} />
                <Route path='/admin/home' element={<AdminProtectedRoute Cmp={AdminHome} />} />
                <Route path="/admin/addcategory" element={<AdminProtectedRoute Cmp={AdminHome} />} />
                <Route path='/admin/addtype' element={<AdminProtectedRoute Cmp={AdminHome} />} />



                { /* Profile Any User */}
                <Route path="/admin/profile" element={ <AdminProtectedRoute Cmp={Profile} />} />
                <Route path="/seller/profile" element={ <SellerProtectedRoute Cmp={Profile} />} />
                <Route path="/profile" element={ <CustomerProtectedRoute Cmp={Profile} />} />

                { /* Logout Any user */}
                <Route path="/logout" element={<Logout />} />

            </Routes>
            <Footer />
        </>
    );
};

export default App;