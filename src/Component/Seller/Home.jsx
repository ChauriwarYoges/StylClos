import { useState, useEffect } from "react";
import './home.css';
import sellerService from "../../Services/seller.service";
import productService from "../../Services/product.service";

function getLocalItems() {
    let list = JSON.parse(localStorage.getItem('user'));
    console.log(list)
    if (list)
        return list;
    else
        return null;
}

const Home = () => {

    let [user] = useState(getLocalItems());
    let [Product, setProduct] = useState([]);

    useEffect(() => {
        sellerService.getProductBasedOnSeller(user.id)
            .then(response => {
                // if (response != null)
                //     alert("got product list");
                setProduct(response.data);
                console.log("respose data : " + response.data);

            })
            .catch(error => {
                console.log("enable to retrieve data" + error);
            });

        sessionStorage.setItem('prodtls', null);
    }, [])

    return (
        <div>
            <div style={{ float: "right", paddingRight: "100px", paddingTop: "20px" }}>
                <a href="addproduct">
                    <button className="btn btn-primary" style={{ float: "right", backgroundColor: "mediumseagreen", marginBottom: "10px" }}>
                        Add Product
                    </button>
                </a>
            </div>
            <section id="header" style={{ paddingLeft: "100px", paddingRight: "100px" }} className='d-flex align-items-center'>
                <div className='container-fluid nav_bg'>
                    <table className="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Title</th>
                                <th scope="col">Image</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                Product ?
                                Product.map(
                                    product =>
                                        <tr>
                                            <td>{product.title}</td>
                                            <td>
                                                <img src={`data:${product.imgType};base64,${product.imgData}`} alt={product.image} width="150px" height="150px" />
                                            </td>
                                            <td>&#x20B9;&nbsp;{product.price}</td>
                                            <td>{product.qauntity}</td>
                                            <td>
                                                <div>
                                                    <a href={'productedit/' + product.id}>
                                                        <button className="btn btn-primary" style={{ backgroundColor: "mediumseagreen", marginBottom: "10px" }}>
                                                            <i className='far fa-edit' style={{ fontSize: "18px", color: "white" }} />
                                                        </button>
                                                    </a>
                                                    &emsp;
                                                    <a href={'/product/delete/' + product.id}>
                                                        <button className="btn btn-primary" style={{ backgroundColor: "red", marginBottom: "10px" }}>
                                                            <i className='far fa-trash-alt' style={{ fontSize: '18px', color: 'white' }} />
                                                        </button>
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                )
                                :
                                <tr>
                                    <td colSpan={5} align="center">NO PRODUCTS ADDED TO SELL.</td>
                                </tr>
                            }
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    );
}

export default Home;