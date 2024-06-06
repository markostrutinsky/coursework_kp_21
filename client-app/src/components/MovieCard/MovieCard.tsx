import React from 'react';
import './MovieCard.scss';
import {useNavigate} from "react-router-dom";
import {PawnItem, PawnItemCategory} from "../../model/PawnItem";
import PawnshopService from '../../services/pawnItemService';

interface ItemCardProps {
    item: PawnItem;
    deletable?: boolean
}

const ItemCard: React.FC<ItemCardProps> = ({ item, deletable }) => {
    
    const navigate = useNavigate();
    function handleRedirect() {
        navigate(`/item/${item.id}`);
    }

    const handleDelete = (e : React.MouseEvent) => {
        const service = new PawnshopService();
        service.delete(item.id).then(()=>{
            window.location.reload();
        }
        );
        e.stopPropagation();
    }
    
    return (
        <div className={"card"} onClick={handleRedirect}>
            <h2 className={"title"}>{item.pawnItemName}</h2>
            <p className={"genre"}>{item.category}</p>
            {deletable ? 
            <button className='delete-button' onClick={handleDelete}>Delete</button>
            :null}
        </div>
    );
};

export default ItemCard;