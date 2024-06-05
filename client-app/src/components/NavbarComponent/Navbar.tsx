import {FC, useState} from "react";
import './Navbar.scss';

interface NavbarComponentProps {
    title: string;
}

const Navbar: FC<NavbarComponentProps> = (props) => {
    const [dropdownOpen, setDropdownOpen] = useState(false);

    function openDropdown() {
        setDropdownOpen(!dropdownOpen);
    }

    return (
        <nav className={"navbar space-between"}>
            <div style={{opacity: 0}}>&lt;</div>
            <div className={'navbar-title'}>{props.title}</div>
            <div className={"left-angle"} onClick={openDropdown}>≡</div>
            {dropdownOpen ? <div className={"dropdown"}>
                <a href={"/movies"}>Movies</a>
                <a href={"/profile"}>Profile</a>
                <a href={"/create-movie"}>Create movie (admin only)</a>
                <a href={"/"}>Logout</a> 
            </div> : null}
        </nav>
    );
}

export default Navbar;