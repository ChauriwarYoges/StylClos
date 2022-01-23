import React from 'react';
import './home.css';
import Collection from '../Collections/Collection';
import Header from './Header';

function Home() {
    return (<>
        <Header />

        <Collection />

        <section id="header" className='d-flex align-items-center'>
            <div className='container-fluid nav_bg'>
                <div className='row'>
                    <div className='col-10 mx-auto'>

                        <div className='col-md-6 pt-5 pt-lg-0 order-2 order-lg-1'>
                            <div className='row'>
                                <h1>Grow your business with <strong className='brand-name'>StyClos</strong></h1>
                                <h2 className='my-3'>
                                    We are the team of talented developer making websites
                                </h2>
                                <div className='my-3'>
                                    <a href='' className='btn-get-started'>
                                        Get Started
                                    </a>
                                </div>
                                {/* <div className='header-img'>
                                    <img src="../images/blackJacket1.jpg" className='img-fluid animated' alt="home-img" />
                                </div> */}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </>);
}

export default Home;