import React from "react";
import { Link } from 'react-router-dom';
import './Form.css';

function Login() {

    let red = { color: "red" };
    let padleft = {paddingLeft: '30px'};

    return (
        <>
            <div className="contains">
                <div className="info">
                    <div className="sign_text">
                        <h2>INFORMATION</h2>
                        <p style={{ marginLeft: '50px', marginRight: '50px', textAlign: 'left', fontSize: '0.8em' }}>
                            Our company is very courageous you will find your dream residing us.
                        </p>
                        <br />
                        <button type="button" id="accbtn"><Link style={{textDecoration:'none'}} to='/Signup'><b>Don't have an Account</b></Link></button>
                    </div>
                </div>
                <div id="signin">
                    <form className="sign_form" style={padleft} method='post'>
                        <h2 style={{ color: "rgb(71, 167, 231)" }}>LOGIN</h2>
                        <table>
                            <tr><td>Email<span style={red}>*</span> </td></tr>
                            <tr><td><input type="text" className="usermail" name="login" required autoFocus /></td></tr>
                            <tr><td><br /></td></tr>
                            <tr><td>Password<span style={red}>*</span> </td></tr>
                            <tr><td><input type="password" className="userpass" name="login" required /></td></tr>
                            <tr><td><br /><br /></td></tr>
                        </table>
                        <input type="submit" value="LOGIN" id="loginbtn" name="login" />
                    </form>
                </div>
            </div>
        </>
    );
}

export default Login;
