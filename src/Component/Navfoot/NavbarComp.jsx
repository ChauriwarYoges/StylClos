import React, { useState } from "react";
import { NavLink, Link } from "react-router-dom";
import './navbar.css';


const NavbarComp = () => {

    let activeStyle = {
        borderBottom: '1px solid #565387',
        textDecoration: "none",
        color: '#3498ab'
    };

    let inactiveStyle = {
        textDecoration: "none",
    }

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
                            <form className="navbar-nav ms-auto mb-2 mb-lg-0">
                                <input className="form-control1 me-2" type="search" placeholder="Search" aria-label="Search" />
                                <button className="btn btn-outline" type="submit">
                                    <i className="fas fa-search fa-1.5x"></i>
                                </button>
                            </form>
                            <ul className="navbar-nav ms-auto mb-2 mb-lg-0">

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

                                {/* <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to='/ContactUs'>
                                    <li className="nav-item">
                                        Contact Us</li>
                                </NavLink> */}

                                <NavLink to={'/Login'}>
                                    <button className="cart-btn">
                                        <i className="fas fa-shopping-cart fa-1.5x"></i>
                                    </button>
                                </NavLink>

                                <li>
                                    <Link style={{ textDecoration: 'none' }} to='/Signup'>
                                        <button className="btn btn-style">Sign up</button>
                                    </Link>
                                </li>
                                <li>
                                    <Link style={{ textDecoration: 'none' }} to='/Login'>
                                        <button className="btn btn-style btn-style-border">Log in</button>
                                    </Link>
                                </li>
                            </ul>

                        </div>
                    </div>
                </nav>
            </section>
        </>

    );





}

export default NavbarComp;