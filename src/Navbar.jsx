import React from "react";
import { NavLink } from "react-router-dom";

const Navbar = () => {

    let activeStyle = {
        fontWeight: 'bold',
        borderBottom: '2px solid #565387',
        textDecoration: "none",
        paddingLeft:'10px',
        paddingRight: '10px',
        color:'#3498ab'
    };

    let inactiveStyle = {
        fontWeight: 'normal',
        textDecoration: "none",
        paddingLeft:'10px',
        paddingRight: '10px'
    }

    return (
        <>
            <div className="container-fluid nav_bg">
                <div className="row">
                    <div className="col-10 mx-auto">
                        <nav className="navbar navbar-expand-lg navbar-light bg-light">
                            <div className="container-fluid">
                                <a className="navbar-brand" href="Home">StyClos</a>
                                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                                    <span className="navbar-toggler-icon"></span>
                                </button>
                                {/* <div className="collapse navbar-collapse" id="navbarSupportedContent"> */}
                                    <ul className="navbar-nav ml-auto mb-2 mb-lg-0">
                                        <li className="nav-item">
                                            <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to="Home">
                                                Home
                                            </NavLink>
                                        </li>
                                        <li className="nav-item">
                                            <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to="Services">
                                                Services
                                            </NavLink>
                                        </li>
                                        <li className="nav-item">
                                            <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to="About">
                                                About
                                            </NavLink>
                                        </li>
                                        <li className="nav-item">
                                            <NavLink style={({ isActive }) => isActive ? activeStyle : inactiveStyle} to="ContactUs">
                                                Contact Us
                                            </NavLink>
                                        </li>
                                    </ul>
                                {/* </div> */}
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </>
    );
}

export default Navbar;