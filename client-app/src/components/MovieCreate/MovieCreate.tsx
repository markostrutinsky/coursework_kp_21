import React, { ChangeEventHandler, useEffect, useState } from 'react';
import './MovieCreate.scss';
import { Jewelry } from '../../model/Jewelry';
import Navbar from "../NavbarComponent/Navbar"; // Adjust the import path
import PawnshopService from '../../services/pawnItemService';
import { Clothes } from '../../model/Clothes';
import { SportEquipment } from '../../model/SportEquipment';
import { Antiques } from '../../model/Antiques';
import { Electronics } from '../../model/Electronics';
import { Furniture } from '../../model/Furniture';
import { HouseholdGoods } from '../../model/HouseholdGoods';
import { MusicalInstrument } from '../../model/MusicalInstrument';
import { useAuth } from '../../hooks/useAuth';

const ItemCreate: React.FC = () => {
    const [category, setCategory] = useState<string>("Jewelry")
    const {authUser} = useAuth()
    
    const jewelryMap = new Map([["pawnItemName", "Item name"], 
    ["metalSample", "Metal Sample"], 
    ["weight", "Weight"], 
    ["size", "Size"],
    ["isPreciousStones", "Precious stones (true/false)"], 
    ["stonesCount", "Stone Count"]])
    
    const clothesMap = new Map([["pawnItemName", "Item name"],
    ["size", "Size"],
    ["brand", "Brand"],
    ["clothingType", "Clothing Type"],
    ["material", "Material"],
    ["condition", "Condition"]])
    
    const sportMap = new Map([["pawnItemName", "Item name"],
    ["sportType", "Sport Type"],
    ["brand", "Brand"],
    ["equipmentType", "Equipment Type"],
    ["size", "Size"],
    ["condition", "Condition"],
    ["model", "Model"]])
    
    const antiquesMap = new Map([["pawnItemName", "Item name"],
    ["age", "Age"],
    ["originality", "Originality"],
    ["condition", "Condition"],
    ["material", "Material"],
    ["maker", "Maker"],
    ["provenance", "Provenance"]])

    const electronicsMap = new Map([["pawnItemName", "Item name"],
    ["year", "Year"],
    ["brand", "Brand"],
    ["model", "Model"],
    ["productType", "Product Type"],
    ["screenSize", "Screen Size"],
    ["storageCapacity", "Storage Capacity"]])
    
    const furnitureMap = new Map([["pawnItemName", "Item name"],
    ["material", "Material"],
    ["style", "Style"],
    ["width", "Width"],
    ["height", "Height"],
    ["depth", "Depth"],
    ["age", "Age"],
    ["brand", "Brand"],
    ["condition", "Condition"]])
    
    const householdMap = new Map([["pawnItemName", "Item name"],
    ["material", "Material"],
    ["style", "Style"],
    ["width", "Width"],
    ["height", "Height"],
    ["depth", "Depth"],
    ["age", "Age"],
    ["brand", "Brand"],
    ["condition", "Condition"]])
    
    const musicalMap = new Map([["pawnItemName", "Item name"],
    ["instrumentType", "Instrument Type"],
    ["brand", "Brand"],
    ["condition", "Condition"],
    ["age", "Age"],
    ["model", "Model"]])
    
    const stringObjectMap = new Map<string, any>([
        ["Jewelry", jewelryMap],
        ["Clothes", clothesMap],
        ["SportEquipment", sportMap],
        ["Antiques", antiquesMap],
        ["Electronics", electronicsMap],
        ["Furniture", furnitureMap],
        ["HouseholdGoods", householdMap],
        ["MusicalInstrument", musicalMap]
    ])

    const [formFields, setFormFields] = useState<Map<string, string>>(jewelryMap)
    
    const handleSubmit = () => {
        const inputFieldContainer = document.querySelector("#fields")
        const inputFields = inputFieldContainer?.children!

        const formData : Record<string, string> = {}

        
        for (let index = 0; index < inputFields.length; index++) {
            const child = inputFields[index].lastChild as HTMLInputElement;
            console.log(child);
            
            const name = child.id;
            const value = child.value;

            formData[name] = value
        }

        formData["category"] = category.toUpperCase()
        formData["firstName"] = authUser!.username
        formData["email"] = authUser!.email
        formData["interestRate"] = "3"

        const pawnItemService = new PawnshopService()
        pawnItemService.addItem(formData).then(resp =>{
            console.log(resp.category);
        })

    };
    
    const handleCategoryChange = (e: React.ChangeEvent<HTMLSelectElement>) =>{
        setCategory(e.currentTarget.value)
    }
    
    useEffect(()=>{
        setFormFields(stringObjectMap.get(category));
    }, [category])


    return (
        <div className={"page"}>
            <Navbar title={"Add new item"}/> 
                <div className={"formGroup"}>
                    <label className={"label"} htmlFor="name">Category</label>
                    <select className='input' name="category" id="category" onChange={handleCategoryChange}>
                        <option value="Jewelry">Jewelry</option>
                        <option value="Clothes">Clothes</option>
                        <option value="SportEquipment">Sport equipment</option>
                        <option value="Antiques">Antiques</option>
                        <option value="Electronics">Electronics</option>
                        <option value="Furniture">Furniture</option>
                        <option value="HouseholdGoods">Household goods</option>
                        <option value="MusicalInstrument">Musical instrument</option>
                    </select>
                </div>
                <div id="fields">
                    {[...formFields.keys()].map(entry => {
                        return <div>
                            {formFields.get(entry)}
                            <input className='input' type="text" id={entry}/>
                        </div>
                    })}
                </div>
                <button className={"button"} onClick={handleSubmit}>Add Item</button>
        </div>
    );
};

export default ItemCreate;