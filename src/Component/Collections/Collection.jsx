import React from "react";
import Card from '../Cards/Card';
import NewCollection from './NewCollection';

const Collection = () => {

    return (<>
        <div className='container-fluid nav_bg'>
            <div className='row'>
                <div className='col-10 mx-auto'>
                    <h1>Here are some New Collections</h1>
                    
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