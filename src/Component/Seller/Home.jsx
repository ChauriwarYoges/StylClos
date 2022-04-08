import { useState, useEffect } from "react";
import './home.css';
import productService from "../../Services/product.service";

const Home = () => {

    let [Product, setProduct] = useState([]);
    let [ proImage, setProImage ] = useState(null);
    let [ fileBase64String, setFileBase64String ] = useState("");

    function decodeFileBase64 (base64String) {
        // From Bytestream to Percent-encoding to Original string
        return decodeURIComponent(
          atob(base64String)
            .split("")
            .map(function (c) {
              return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
            })
            .join("")
        );
      };
    
      const decodeBase64 = decodeFileBase64(
        fileBase64String.substring(fileBase64String.indexOf(",") + 1)
      );

    useEffect(() => {
        productService.getAll()
            .then(response => {
                // if (response != null)
                //     alert("got product list");
                setProduct(response.data);
                console.log("respose data : " + response.data);
                console.log("Product data : ");

            })
            .catch(error => {
                console.log("enable to retrieve data" + error);
            });

        sessionStorage.setItem('prodtls', null);
    }, [])

    return (<>
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
                                                {                                                    
                                                    productService.getImage(product.image)
                                                        .then(response => {
                                                            console.log("Image " + product.image);
                                                            console.log("response " + response );
                                                            console.log("response data : " + response.data);
                                                            setProImage(response.data);
                                                            console.log(proImage);
                                                            <img style={{ height: '50px', width: '50px' }} src={`data:image/jpg;base64,${response.data.imgData}`} alt='not avaiable' />
                                                        })
                                                        .catch(err => {
                                                            console.log(err);
                                                        })
                                                }
                                                {/* <img style={{ height: '50px', width: '50px'}} alt = 'not available' /> */}
                                            </td>
                                            <td></td>
                                            <td>{ }</td>
                                            <td>
                                                <div>
                                                    <a href={'/product/edit/' + product.id}>
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
                                null
                        }
                        <tr>
                            <td>I love it</td>
                            <td>Awesome</td>
                            <td>Profitable</td>
                            <td>keep it up</td>

                        </tr>
                        <tr>
                            <td>I love it</td>
                            <td>Awesome</td>
                            <td>Profitable</td>
                            <td>keep it up</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>
    </>);
}

export default Home;