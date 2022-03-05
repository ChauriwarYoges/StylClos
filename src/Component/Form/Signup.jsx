import React from 'react';
import { Link } from 'react-router-dom';
import { useState } from 'react';
import './Form.css';
import customerService from '../../Services/customer.service';

function Signup() {
    const matchPass = () => {
        let pass = document.getElementById("wpass").value;
        let cpass = document.getElementById("cpass").value;

        if (pass == null || pass == "" || pass.trim().length == 0) {
            return false;
        }

        else {
            if (cpass == null || pass == "" || cpass.trim().length == 0) {
                return false;
            }
            else {
                if (pass == cpass) {
                    return true;
                }
                else {
                    alert("PASSWORD NOT MATCHED");
                    cpass.style.boxshadow = "-1px -1px red";
                    return false;
                }
            }
        }
    };
    
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [date] = useState(null);
    const [password, setPassword] = useState('');
    const [confirm, setConfirm] = useState('');
    const [address] = useState(null);

    const signup = (e) => {
        e.preventDefault();
        
        const customer = {email, name, date, password, confirm, address};

        let status = false;
        if(email) {
            customerService.get(email)
                .then(response => {
                    console.log("Email already exists");
                    alert("entered email is already registered");
                    status = true;
                }).catch(error => {
                    console.log("registering new user");
                });

                if (!status) {
                    customerService.signup(customer)
                        .then(response => {
                            alert("registration succesful");
                            console.log("registration sucessful");
                        })
                        .catch(error => {
                            console.log("something went wrong while registration of new customer");
                        });
                }
        }
    }

    return (

        <section className="vh-100" style={{ backgroundColor: '#eee' }}>
            <div className="container h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-lg-12 col-xl-11">
                        <div className="card text-black" style={{ borderRadius: '25px' }}>
                            <div className="card-body p-md-5">
                                <div className="row justify-content-center">
                                    <div className="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">

                                        <p className="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Sign up</p>

                                        <form className="mx-1 mx-md-4" onSubmit={matchPass}>

                                            <div className="d-flex flex-row align-items-center mb-4">
                                                <i className="fas fa-user fa-lg me-3 fa-fw"></i>
                                                <div className="form-outline flex-fill mb-0">
                                                    <input type="text" id="name" value={name} onChange={(e) => setName(e.target.value)} className="form-control" />
                                                    <label className="form-label" htmlFor='name' >Your Name</label>
                                                </div>
                                            </div>

                                            <div className="d-flex flex-row align-items-center mb-4">
                                                <i className="fas fa-envelope fa-lg me-3 fa-fw"></i>
                                                <div className="form-outline flex-fill mb-0">
                                                    <input type="email" id="mail" className="form-control" value={email} onChange={(e) => setEmail(e.target.value)} />
                                                    <label className="form-label" htmlFor='mail'>Your Email</label>
                                                </div>
                                            </div>

                                            <div className="d-flex flex-row align-items-center mb-4">
                                                <i className="fas fa-lock fa-lg me-3 fa-fw"></i>
                                                <div className="form-outline flex-fill mb-0">
                                                    <input type="password" id="wpass" value={password} onChange={(e) => setPassword(e.target.value)} className="form-control" />
                                                    <label className="form-label" htmlFor="wpass">Password</label>
                                                </div>
                                            </div>

                                            <div className="d-flex flex-row align-items-center mb-4">
                                                <i className="fas fa-key fa-lg me-3 fa-fw"></i>
                                                <div className="form-outline flex-fill mb-0">
                                                    <input type="password" id="cpass" value={confirm} onChange={(e) => setConfirm(e.target.value)} className="form-control" />
                                                    <label className="form-label" htmlFor="cpass">Repeat your password</label>
                                                </div>
                                            </div>

                                            <div className="form-check d-flex justify-content-center mb-5">
                                                <input
                                                    className="form-check-input me-2"
                                                    type="checkbox"
                                                    value=""
                                                    id="form2Example3c"
                                                />
                                                <label className="form-check-label" htmlFor="form2Example3">
                                                    I agree all statements in <a href="#!">Terms of service</a>
                                                </label>
                                            </div>

                                            <div className="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
                                                <button type="submit" className="btn btn-primary btn-lg">Register</button>
                                            </div>

                                        </form>

                                    </div>
                                    <div className="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

                                        <img src="images/form/signupImage.webp" className="img-fluid" alt="Sample image" />

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Signup;