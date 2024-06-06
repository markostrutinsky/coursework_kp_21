import React, {FC, useEffect, useState} from "react";
import './MoviesPage.scss';
import ItemCard from "../MovieCard/MovieCard";
import Navbar from "../NavbarComponent/Navbar";
import {PawnItem} from "../../model/PawnItem";
import PawnItemService from "../../services/pawnItemService";

interface MoviesPageComponentProps {
        
}

const ItemsPage: FC<MoviesPageComponentProps> = (props) => {
    const [pawnItems, setPawnItems] = useState<PawnItem[]>([])
    const movie : PawnItemService = new PawnItemService();
    useEffect(() => {
        movie.getAll().then((data) => {
            console.log(data);
            setPawnItems(data);
        })
    }, [])

    return (
        <div className={"page"}>
            <Navbar title={"Items"}/>
            <div className={'movies-grid'}>
                {pawnItems.map(item => (
                    <ItemCard key={item.id} item={item} />
                ))}
            </div>
        </div>
    );
}

export default ItemsPage;