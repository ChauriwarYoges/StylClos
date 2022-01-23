import React from 'react';
import styled from 'styled-components';


const Footer = () => {
    return (
        <>

            <FooterContainer className='main-footer'>
                <div className='footer-middle'>
                    <div className='container'>
                        <div className='row'>
                            <div className='col-md-3 col-sm-6'>
                                {/* Column 1 */}
                                <h4>Lorem ipsum</h4>
                                <ul className='list-unstyled'>
                                    <li>Lorem ipsum</li>
                                    <li>Lorem ipsum</li>
                                    <li>Lorem ipsum</li>
                                    <li>Lorem ipsum</li>
                                </ul>
                            </div>
                            <div className='col-md-3 col-sm-6'>
                                {/* Column 2 */}
                                <h4>Lorem ipsum</h4>
                                <ul className='list-unstyled'>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                </ul>
                            </div>
                            <div className='col-md-3 col-sm-6'>
                                {/* Column 3 */}
                                <h4>Lorem ipsum</h4>
                                <ul className='list-unstyled'>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                </ul>
                            </div>
                            <div className='col-md-3 col-sm-6'>
                                {/* Column 4 */}
                                <h4>Lorem ipsum</h4>
                                <ul className='list-unstyled'>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                    <li><a href='/'>Lorem ipsum</a></li>
                                </ul>
                            </div>
                        </div>

                        {/* Footer Bottom */}
                        <div className='footer-bottom'>
                            <p className='text-xs-center'>
                                &copy;{new Date().getFullYear()} Style Clothing Website - All Rights Resvered
                            </p>
                        </div>
                    </div>
                </div>
            </FooterContainer>
        </>

    )
}

export default Footer;

const FooterContainer = styled.footer`
    .main-footer {
        bottom: 0;
    }

    .footer-middle {
        background: var(--mainDark);
        padding-top: 3rem;
        color: var(--mainWhite);
    }

    .footer-bottom {
        padding-top: 3rem;
        padding-bottom: 2rem;
    }

    ul li a {
        color: var(--mainGrey);
    }

    ul li a:hover {
        color: var(--mainLightGrey);
    }
`;