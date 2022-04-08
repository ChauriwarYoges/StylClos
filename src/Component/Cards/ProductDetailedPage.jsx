import React, { useState, useEffect } from 'react';
import "./productpage.css";

import productService from '../../Services/product.service';
import { useParams } from 'react-router-dom';
import Unauthorized from '../Unauthorized';

const ProductDetailedPage = () => {

    let { id } = useParams();
    let [product, setProduct] = useState(null);
    let [data, setData] = useState(null);

    useEffect(() => {
        console.log("product id : " + id);
        productService.getProductById(id)
            .then(response => {
                console.log(response.data);
                product = (response.data);
                console.log("product details : " + product);
                productService.getImage(product.image)
                    .then(response => {
                        console.log(response.data);
                        data = (response.data);
                        console.log("image name : " + data.name);
                    })
                    .catch(err => {
                        console.log("in err image : " + err);
                    });
            })
            .catch(err => {
                console.log("in err : " + err);
            });


    }, [])



    return (<>
        <div className='productpage'>
            <div className="ppcontainer py-4 my-4 mx-auto d-flex flex-column">
                <div className="header">
                    <div className="row r1">
                        <div className="col-md-9 abc">
                            <h1>{(product) ? product.title : "INVALID PRODUCT PAGE"}</h1>
                        </div>
                    </div>
                </div>
                <div className="container-body mt-4">
                    <div className="row r3">
                        <div className="col-md-5 p-0 klo">
                            <ul>
                                <li className='ppli'>100% Quality</li>
                                <li className='ppli'>Free Shipping</li>
                                <li className='ppli'>Easy Returns</li>
                                <li className='ppli'>12 Months Warranty</li>
                                <li className='ppli'>EMI Starting from (On Credit Cards)</li>
                                <li className='ppli'>Normal Delivery : 4-5 Days</li>
                                <li className='ppli'>Express Delivery : 2-3 Days</li>
                                <li className='ppli'>COD Available (All Over India)</li>
                            </ul>
                        </div>
                        <div className="col-md-7"> 
                        <img src={`data:image/jpg;base64,${(data) ? data.imgData : null}`} alt={(data) ? data.name : null} width="90%" height="95%" /> </div>
                    </div>
                </div>
                <div className="footer d-flex flex-column mt-5">
                    <div className="row r4">
                        <div className="col-md-2 myt des"><a href="#">Description</a></div>
                        <div className="col-md-2 mio offset-md-4"><a href="#">ADD TO CART</a></div>
                        <div className="col-md-2 myt "><button type="button" className="btn btn-outline-warning"><a href="#">BUY NOW</a></button></div>
                    </div>
                </div>
            </div>
        </div>
    </>);

}

export default ProductDetailedPage;