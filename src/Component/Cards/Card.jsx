import React from "react";
import { Link } from "react-router-dom";
// import ProductDetailedPage from './ProductDetailedPage';
import './cards.css';


const Card = (props) => {
    return (
        <>
            <Link  to={'/ProductDetailedPage'}>
                <div className="cards">
                    <div className="card_image">
                        <img src={props.imgsrc} height={400} width={200} alt="loading failed" />
                    </div>
                    <div className="card_title">
                        <span className="card_category">{props.title}</span>
                    </div>
                    <div className="card_info">
                        <h3 className="card_name">{props.sname}</h3>
                        <a href={props.link} target="_blank" className="card_ref">
                            <button className="card_btn"><i class="fas fa-money-bill-wave-alt"></i> BUY NOW </button>
                        </a>
                        <a href='/Login'>
                            <button className="card_cart_btn"><i class="fas fa-cart-plus"></i> ADD CART</button>
                        </a>
                    </div>
                </div>
            </Link>
        </>
    );
}

export default Card;