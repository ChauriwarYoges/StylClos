import React from "react";
import './cards.css';


const Card = (props) => {
    return (
        <>
            <div className="col-12 col-md-9 col-lg-3 cards">
                <div className="card_image">
                    <img src={props.imgsrc} height={400} width={200} alt="loading failed" />
                </div>
                <div className="card_title">
                    <span className="card_category">{props.title}</span>
                </div>
                <div className="card_info">
                    <h3 className="card_name">{props.sname}</h3>
                    <a href={props.link} target="_blank" className="card_ref">
                        <button className="card_btn"> BUY NOW </button>
                    </a>
                </div>
            </div>
        </>
    );
}

export default Card;