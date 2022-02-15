import React from "react";
import Card from '../Cards/Card';
import NewCollection from './NewCollection';
import './collection.css';

const Collection = () => {

    return (<>
        <div className='container-fluid collection'>
            <div className='row'>
                <div className='col-10 mx-auto'>
                    <h1 style={{fontWeight:'800'}}>
                    New Arrivals
                    </h1>
                    
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

export default Collection;