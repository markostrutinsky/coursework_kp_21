import React from 'react';
import './MovieCard.scss';
import {useNavigate} from "react-router-dom";
import {PawnItem, PawnItemCategory} from "../../model/PawnItem";

interface MovieCardProps {
    item: PawnItem;
}

const ItemCard: React.FC<MovieCardProps> = ({ item }) => {
    
    const navigate = useNavigate();
    function handleRedirect() {
        navigate(`/item/${item.id}`);
    }
    
    return (
        <div className={"card"} onClick={handleRedirect}>
            <h2 className={"title"}>{item.pawnItemName}</h2>
            <p className={"genre"}>{PawnItemCategory[item.category]}</p>
        </div>
    );
};

export default ItemCard;