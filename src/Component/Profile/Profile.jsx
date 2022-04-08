import './profile.css';
import { useState, useEffect } from 'react';
import customerService from '../../Services/customer.service';
import { useNavigate } from 'react-router-dom';
import Logout from '../Form/Logout';

const getLocalItems = () => {
    let list = localStorage.getItem('user');
    console.log("Profile : " + list);

    if (list)
        return JSON.parse(list);
    else {
        return null;
    }
}


const Profile = () => {

    let [user] = useState(getLocalItems());

    const nav = useNavigate();
    let [userState, setUserState] = useState(null);

    let [address, setAddress] = useState( null );

    function fetchAddress(user) {
        if(user == null)
            return null;

        console.log("fetchAddress : " + (user != null) + " " + user.name);
        customerService.getAddress(user.id)
            .then(response => {
                setAddress(response.data);
            })
            .catch(error => {
                console.log("Address Not found " + error);
            });
    }

    useEffect(() => {
        if(user != null)
        fetchAddress(user);
    }, [])

    

    // TO TOGGLE EDIT AND SAVE BUTTONS
    let [editBtn, setEditBtn] = useState(true);
    let [details, setDetails] = useState(true);

    let [userId] = useState((user) ? user.id : null);
    let [name, setName] = useState((user) ? user.name : null);
    let [addId] = useState((address) ? address.id : null);
    let [areaName, setAreaName] = useState((address) ? address.areaName : null);
    let [city, setCity] = useState((address) ? address.city : null);
    let [state, setState] = useState((address) ? address.state : null);
    let [country, setCountry] = useState((address) ? address.country : null);
    let updatedData = { userId, name, addId, areaName, city, state, country };



    function edit() {
        console.log("in edit() " + details + " " + editBtn);
        setEditBtn(!editBtn);
        setDetails(!details);
        console.log("leaving edit() " + details + " " + editBtn);
    }

    function saveChanges(e) {
        e.preventDefault();

        console.log(updatedData);

        customerService.updateProfile(updatedData)
            .then(response => {
                alert("Profile Updated Successfully");
                console.log(response.data);
                customerService.get(user.email)
                    .then(response => {
                        setUserState(response.data);
                        nav("/profile");
                    })
                    .catch(err => {
                        console.log("Unable to update : " + err);
                        if (!err?.response) {
                            alert('No Server Response');
                        } else if (err.response?.status === 400) {
                            alert('Bad Request');
                        } else if (err.response?.status === 401) {
                            alert('Unauthorized');
                        } else {
                            alert('Data retrival usnsuccessful');
                        }
                    });
                edit();
            })
            .catch(err => {
                console.log("Unable to update : " + err);
                if (!err?.response) {
                    alert('No Server Response');
                } else if (err.response?.status === 400) {
                    alert('Bad Request');
                } else if (err.response?.status === 401) {
                    alert('Unauthorized');
                } else {
                    alert('Update Unsuccessful');
                }
            });
    }

    function deactivate() {
        console.log("Deactivation process in progress");
    }

    const handleLogout = () => {
        nav("/logout");
    }


    return (<>
        <div className='profilepage'>
            <div className="container rounded bg-white mt-5 mb-5">
                <div className="row">
                    <div className="col-md-3 border-right">
                        <div className="d-flex flex-column align-items-center text-center p-3 py-5">
                            <img className="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg" />
                            <span className="font-weight-bold">{(user) ? user.name : ""}</span>
                            <span className="text-black-50">{(user) ? user.email : ""}</span><span> </span>
                        </div>
                    </div>
                    <div className="col-md-5 border-right">
                        <div className="p-3 py-5">
                            {
                                details ?
                                    <>
                                        <div className="d-flex justify-content-between align-items-center mb-3">
                                            <h4 className="text-right">Account Details</h4>
                                        </div>
                                        <div className="row mt-2">
                                            <div className="col-md-6"><label className="labels">Name</label><input type="text" className="form-control" placeholder="first name" onChange={(e) => setName(e.target.value)} value={(user) ? user.name : ""} readOnly /></div>
                                        </div>
                                        <div className="row mt-3">
                                            <div className="col-md-12"><label className="labels">Area</label><input type="text" className="form-control" placeholder="enter address line" onChange={(e) => setAreaName(e.target.value)} value={(address) ? address.areaName : ""} readOnly /></div>
                                            <div className="col-md-12"><label className="labels">City</label><input type="text" className="form-control" placeholder="enter address line" onChange={(e) => setCity(e.target.value)} value={(address) ? address.city : ""} readOnly /></div>
                                        </div>
                                        <div className="row mt-3">
                                            <div className="col-md-6"><label className="labels">State</label><input type="text" className="form-control" onChange={(e) => setState(e.target.value)} value={(address) ? address.state : ""} placeholder="state" readOnly /></div>
                                            <div className="col-md-6"><label className="labels">Country</label><input type="text" className="form-control" placeholder="country" onChange={(e) => setCountry(e.target.value)} value={(address) ? address.country : ""} readOnly /></div>
                                        </div>
                                    </>
                                    :
                                        <form onSubmit={(e) => saveChanges(e)}>
                                            <div className="d-flex justify-content-between align-items-center mb-3">
                                                <h4 className="text-right">Edit Details</h4>
                                            </div>
                                            <div className="row mt-2">
                                                <div className="col-md-6">
                                                    <label className="labels">Name</label>
                                                    <input type="text" name='name' className="form-control" value={(user) ? user.name : null} onChange={(e) => setName(e.target.value)} autoFocus />
                                                </div>
                                            </div>
                                            <div className="row mt-3">
                                                <div className="col-md-12">
                                                    <label className="labels">Area</label>
                                                    <input type="text" name='area' className="form-control" value={(address) ? address.areaName : null} onChange={(e) => setAreaName(e.target.value)} />
                                                </div>
                                                <div className="col-md-12">
                                                    <label className="labels">City</label>
                                                    <input type="text" name='city' className="form-control" value={(address) ? address.city : null} onChange={(e) => setCity(e.target.value)} />
                                                </div>
                                            </div>
                                            <div className="row mt-3">
                                                <div className="col-md-6">
                                                    <label className="labels">State</label>
                                                    <input type="text" name='state' className="form-control" onChange={(e) => setState(e.target.value)} value={(address) ? address.state : null} />
                                                </div>
                                                <div className="col-md-6">
                                                    <label className="labels">Country</label>
                                                    <input type="text" name='country' className="form-control" value={(address) ? address.country : null} onChange={(e) => setCountry(e.target.value)} />
                                                </div>
                                            </div>
                                            <div className="mt-5 text-center">
                                                <button className="btn btn-primary profile-button" type="submit">Save Profile</button>
                                                &emsp;
                                                <button className="btn btn-primary cancel-button" onClick={edit} type="button">Cancel</button>
                                            </div>
                                        </form>

                            }
                        </div>
                        {
                            editBtn ?
                                <div className='col-md-2 border-right' id='profileEdit' >
                                    <button className="btn btn-primary" onClick={edit} style={{ backgroundColor: "mediumseagreen", marginBottom: "10px" }}>
                                        <i className='far fa-edit' style={{ fontSize: "18px", color: "white" }} />
                                    </button> &emsp;
                                    <button className="btn btn-primary cancel-button" onClick={deactivate} type="button">Deactivate</button>
                                    
                                    <button style={{float:'right'}} className="btn btn-primary logout-button" onClick={handleLogout} type="button">LOG OUT</button>
                                </div>
                                : null
                        }
                    </div>
                </div>
            </div>
        </div>
    </>);

}

export default Profile;