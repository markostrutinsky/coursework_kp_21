import React, {FC, useEffect, useState} from "react";
import './MoviesPage.scss';
import ItemCard from "../MovieCard/MovieCard";
import Navbar from "../NavbarComponent/Navbar";
import {PawnItem, PawnItemCategory} from "../../model/PawnItem";
import PawnItemService from "../../services/pawnItemService";
import { log } from "console";

interface MoviesPageComponentProps {
        
}

const ItemsPage: FC<MoviesPageComponentProps> = (props) => {
    const [pawnItems, setPawnItems] = useState<PawnItem[]>([])
    const movie : PawnItemService = new PawnItemService();
    const [category, setCategory] = useState<PawnItemCategory|"All">("All")

    useEffect(() => {
        if(category != "All"){
            movie.getAllByCategory(category).then((data)=>{
                console.log(data);
                setPawnItems(data);
            })
        }else{
            movie.getAll().then((data) => {
                console.log(data);
                setPawnItems(data);
            })
        }
    }, [category])

    const handleCategoryChange = (e : React.ChangeEvent<HTMLSelectElement>) =>{
        const value = e.currentTarget.value as unknown as PawnItemCategory|"All";
        setCategory(value);
    }

    return (
        <div className={"page"}>
            <Navbar title={"Items"}/>
            <select className='input' name="category" id="category" onChange={handleCategoryChange}>
                        <option value={undefined}>All</option>
                        <option value={PawnItemCategory.JEWELRY}>Jewelry</option>
                        <option value={PawnItemCategory.CLOTHES}>Clothes</option>
                        <option value={PawnItemCategory.SPORTSEQUIPMENT}>Sport equipment</option>
                        <option value={PawnItemCategory.ANTIQUES}>Antiques</option>
                        <option value={PawnItemCategory.ELECTRONICS}>Electronics</option>
                        <option value={PawnItemCategory.FURNITURE}>Furniture</option>
                        <option value={PawnItemCategory.HOUSEHOLDGOODS}>Household goods</option>
                        <option value={PawnItemCategory.MUSICALINSTRUMENTS}>Musical instrument</option>
                    </select>
            <div className={'movies-grid'}>
                {pawnItems.map(item => (
                    <ItemCard key={item.id} item={item} />
                ))}
            </div>
        </div>
    );
}

export default ItemsPage;