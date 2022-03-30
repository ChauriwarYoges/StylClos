import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import userService from "../../Services/user.service";

function Login() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role] = useState('SELLER');
    const nav = useNavigate();

    const signin = (e) => {
        e.preventDefault();

        const user = {email, password, role};

        if(email) {
            userService.signin(user)
                .then(response => {
                    alert("Role : " + response.data.role);
                    console.log("login successful", response.data);
                    
                    return true;
                })
                .catch(error => {
                    alert("Invalid email/password");
                });
        }
        else {
            alert("Invalid email/password");
        }
        return false;
    } 

    return (
        <section className="vh-100" style={{ backgroundColor: '#eee' }}>
            <div className="container py-5 h-100">
                <div className="row d-flex justify-content-center align-items-center h-100">
                    <div className="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div className="card shadow-2-strong" style={{ borderRadius: '1rem' }}>
                            <div className="card-body p-5 text-center">

                                <h3 className="mb-5">Login</h3>
                                <form action="/home" onSubmit={(e) => signin(e)}>
                                    <div className="form-outline mb-4">
                                        <label className="form-label" htmlFor="typeEmailX-2">Email</label>
                                        <input type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} className="form-control form-control-lg" />
                                    </div>

                                    <div className="form-outline mb-4">
                                        <label className="form-label" htmlFor="typePasswordX-2">Password</label>
                                        <input type="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} className="form-control form-control-lg" />
                                    </div>

                                    {/*Checkbox*/}
                                    <div className="form-check d-flex justify-content-start mb-4">
                                        <input
                                            className="form-check-input"
                                            type="checkbox"
                                            value=""
                                            id="form1Example3"
                                        />
                                        <label className="form-check-label" htmlFor="form1Example3"> &emsp;Remember Me </label>
                                    </div>

                                    <button type="submit" className="btn btn-primary btn-lg btn-block">Login</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default Login;
