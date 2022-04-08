import React, { useEffect, useState } from "react";
import Card from '../Cards/Card';
import NewCollection from './NewCollection';
import './collection.css';
import productService from "../../Services/product.service";

const Collection = () => {

    let [products, setProducts] = useState(null);

    useEffect(() => {
        productService.getAll()
            .then(response => {
                console.log("Responsed Products Data " + response.data);
                setProducts(response.data);
            })
            .catch(err => {
                console.log("error while getting products data : " + err);
            })
    }, [])

    return (<>
        <div className='container-fluid collection'>
            <div className='row'>
                <div className='col-10 mx-auto'>
                    <h1 style={{ fontWeight: '800' }}>
                        New Arrivals
                    </h1>

                    <div className="collection-container" style={{ display: 'flex', flexWrap: 'wrap' }}>

                        {
                            products ?
                                products.map((p) => {
                                    return (<Card key={p.id}
                                        id = {p.id}
                                        imgType = {p.imgType}
                                        imgsrc={p.imgData}
                                        title={p.category.category + " " + p.type.typeName}
                                        sname={p.title}
                                        link={p.size}
                                    />);
                                })
                                :

                                null
                        }

                    </div>

                </div>
            </div>
        </div>
    </>);

}

export default Collection;