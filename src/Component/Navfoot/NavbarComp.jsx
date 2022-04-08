import React, { useState } from "react";
import { NavLink, Link } from "react-router-dom";
import './navbar.css';
// import { Changelogin } from "./Changelogin";

const getLocalItems = () => {
    let list = localStorage.getItem('user');
    console.log("Changelogin : " + list);
    if (list)
        return JSON.parse(list);
    else
        return null;
}

let activeStyle = {
    borderBottom: '1px solid #565387',
    textDecoration: "none",
    color: '#3498ab'
};

let inactiveStyle = {
    textDecoration: "none",
}

const NavbarComp = () => {

    const [user] = useState(getLocalItems());



    const [show, setShow] = useState(false);

    return (
        <>
            <section className="navbar-bg">
                <nav className="navbar navbar-expand-lg navbar-light">
                    <div className="container ">
                        <a className="navbar-brand" href="/Home">StylClos</a>
                        <button
                            className="navbar-toggler"
                            type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation"
                            onClick={() => setShow(!show)} >
                            <span className="navbar-toggler-icon"></span>
                        </button>
                        <div className={`collapse navbar-collapse ${show ? "show" : ""}`} id="navbarSupportedContent">
                            {/* <form className="navbar-nav ms-auto mb-2 mb-lg-0">
                                <input className="form-control1 me-2" type="search" placeholder="Search" aria-label="Search" />
                                <button className="btn btn-outline" type="submit">
                                    <i className="fas fa-search fa-1.5x"></i>
                                </button>
                            </form> */}
                            {/* <Changelogin /> */}

                            {
                                (user == null) ? (<AnoynmousUser />)
                                    : (user.role == 'ADMIN') ? <AfterAdminLogin />
                                        : (user.role == 'SELLER') ? <AfterSellerLogin />
                                            : <AfterCustomerLogin />

                            }


                        </div>
                    </div>
                </nav>
            </section>
        </>

    );





}


const User = () => {

    return (<>
        <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to='/Home'>
            <li className="nav-item">
                Home</li>
        </NavLink>

        <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to='/Womens'>
            <li className="nav-item">
                Womens</li>
        </NavLink>

        <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to='/Mens'>
            <li className="nav-item">
                Mens</li>
        </NavLink>

        <NavLink to={'/cart'}>
            <button className="cart-btn">
                <i className="fas fa-shopping-cart" style={{ fontSize: "20px" }} /><br />
            </button>
        </NavLink>

        <li>
            <Link style={{ textDecoration: 'none' }} to='/profile'>
                <i className="fa fa-user-circle" style={{ fontSize: "24px" }} /><br />
                <span className="hidden-xs">Profile</span>
            </Link>
        </li>
    </>);
}

const AnoynmousUser = () => {
    return (<>
        <ul className="navbar-nav ms-auto mb-2 mb-lg-0">

            <User />

            <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to={'/seller/login'}>
                <li className="nav-item">
                    <i className='fas fa-wallet' style={{ fontSize: '18px' }} /> <br />
                    <span className="hidden-xs">Sell with us</span>
                </li>
            </NavLink>
        </ul>
    </>);
}

const AfterCustomerLogin = () => {
    return (<>
        <ul className="navbar-nav ms-auto mb-2 mb-lg-0">

            <User />

            <li className="nav-item">
                <Link style={{ textDecoration: 'none' }} to='/logout'>
                    {/* <i className="fa fa-sign-out" style={{fontSize:"24px", color:"red"}} /><br /> */}
                    <span className="glyphicon glyphicon-log-out" /> <br />
                    <small className="hidden-xs">Logout</small>
                </Link>
            </li>

        </ul>
    </>);
}

const AfterSellerLogin = () => {
    return (<>
        <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
            <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to='/seller/Home'>
                <li className="nav-item">
                    Home</li>
            </NavLink>
            <li>
                <Link style={{ textDecoration: 'none' }} to='/seller/profile'>
                    <i className="fa fa-user-circle" style={{ fontSize: "24px" }} /><br />
                    <span className="hidden-xs">Profile</span>
                </Link>
            </li>
            <li className="nav-item">
                <Link style={{ textDecoration: 'none' }} to='/logout'>
                    <i className="fa-solid fa-user" /><br />
                    <span className="hidden-xs">Logout</span>
                </Link>
            </li>
        </ul>
    </>);
}

const AfterAdminLogin = () => {
    return (<>
        <ul className="navbar-nav ms-auto mb-2 mb-lg-0">
            <li className="nav-item">
                Home
            </li>
            <li className="nav-item">
                Admin
            </li>
            <li>
                <Link style={{ textDecoration: 'none' }} to='/admin/profile'>
                    <i className="fa fa-user-circle" style={{ fontSize: "24px" }} /><br />
                    <span className="hidden-xs">Profile</span>
                </Link>
            </li>
            <li className="nav-item">
                <Link style={{ textDecoration: 'none' }} to='/logout'>
                    <i className="fa-solid fa-user" /><br />
                    <span className="hidden-xs">Logout</span>
                </Link>
            </li>
        </ul>
    </>);
}

export default NavbarComp;