import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import { Navigate, Route, Routes } from 'react-router';
import LoginSeller from './Component/Seller/Login';
import SignupSeller from './Component/Seller/Signup';

const SellerApp = () => {
    return (
        <>
            {/*<NavbarComp /> */}
            <Routes>
                <Route path="/" element={<Navigate to="/seller/home" />} />
                <Route path="/seller/login" element={<LoginSeller />} />
                <Route path="/seller/signup" element={<SignupSeller />} />
            </Routes>
            {/* <Footer /> */}
        </>
    );
};

export default SellerApp;