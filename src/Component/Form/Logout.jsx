import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";


const Logout = () => {

    let nav = useNavigate();

    const getLocalItems = () => {
        let list = localStorage.getItem('user');
        console.log("logout : " + list);

        if (list) {
            console.log("return to useEffect");
            return JSON.parse(list);
        } else {
            return [];
        }
    }

    let [users] = useState(getLocalItems());

    useEffect(() => {
        console.log("user : " + users);

        if (users == null) {
            localStorage.setItem('user', null);
            nav("/Home");
        }
        else if (users.role == 'ADMIN') {
            localStorage.setItem('user', null);
            nav('/admin/login');
        }
        else if (users.role == 'SELLER') {
            localStorage.setItem('user', null);
            nav('/seller/login');
        }
        else {
            localStorage.setItem('user', null);
            nav('/Home');
        }
    }, [users])

    return (<>
    </>);
}

export default Logout;