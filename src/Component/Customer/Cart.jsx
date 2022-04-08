import './cart.css';

const Cart = () => {

    return (<>
        <div className='profilepage'>
            <div className="card">
                <div className="row">
                    <div className="col-md-8 cart">
                        <div className="title">
                            <div className="row">
                                <div className="col">
                                    <h4><b>Shopping Cart</b></h4>
                                </div>
                                <div className="col align-self-center text-right text-muted">3 items</div>
                            </div>
                        </div>
                        <div className="row border-top border-bottom">
                            <div className="row main align-items-center">
                                <div className="col-2"><img className="cartimg" src="https://i.imgur.com/pHQ3xT3.jpg" /></div>
                                <div className="col">
                                    <div className="row text-muted">Shirt</div>
                                    <div className="row">Cotton T-shirt</div>
                                </div>
                                <div className="col"> <a className='cartA' href="#">-</a><a href="#" className="cartA">1</a><a className='cartA' href="#">+</a> </div>
                                <div className="col">&#x20B9; 44.00 <span className="close">&#10005;</span></div>
                            </div>
                        </div>
                        <div className="back-to-shop">
                            <a className='cartA' href="/home" style={{ textDecoration: "none" }}>
                                &#8592; &nbsp;<span className="text-muted">Back to shop</span>
                            </a>
                        </div>
                    </div>
                    <div className="col-md-4 summary">
                        <div>
                            <h5><b>Summary</b></h5>
                        </div>

                        <div className="row">
                            <div className="col" style={{ paddingLeft: "0" }}>ITEMS 3</div>
                        </div>
                        <div className="row" style={{ borderTop: "1px solid rgba(0,0,0,.1)", padding: "2vh 0" }}>
                            <div className="col">TOTAL PRICE</div>
                            <div className="col text-right">&#x20B9; 137.00</div>
                        </div> <button className="checkoutbtn">CHECKOUT</button>
                    </div>
                </div>
            </div>
        </div>
    </>);
}

export default Cart;