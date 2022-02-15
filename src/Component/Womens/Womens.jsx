import React from 'react';
import Card from '../Cards/Card';
import NewCollection from './WomenCollection';

function Womens() {
    return (<>
        {/* <h1>We are in Womens Collection</h1> */}
        <div className='container-fluid collection'>
            <div className='row'>
                <div className='col-10 mx-auto'>
                    <h1>
                    New Arrivals for Womens</h1>
                    
                    <div className="collection-container" style={{display:'flex', flexWrap:'wrap'}}>
                    {NewCollection.map((val) => {
                        console.log(val);
                        return (<Card 
                            imgsrc = {val.imgsrc}
                            title = {val.title}
                            sname = {val.sname}
                            link = {val.link}
                        />);
                    })}
                    </div>

                </div>
            </div>
        </div>
    </>);
}

export default Womens;