import {Link, useParams} from "react-router-dom";
import {Sandwich} from "../model/Sandwich"

type SandwichPageProps = {
    sandwiches: Sandwich []
}

export default function SandwichPage(props: SandwichPageProps) {

    const {id} = useParams();
    const sandwich: Sandwich = props.sandwiches.find(sandwich => sandwich.id === id)!

    if (!sandwich) {
        return (<section>Das Sandwich mit der ID {id} wurde nicht gefunden</section>)
    }

    return (
        <section>
            <Link className={"Link"} to={"/"}>Zurück zum Shop</Link>

            <div className={"sandwich-card"}>
                <p className="name">{sandwich.name}</p>
                <p className="left-side">Bullete: </p><p>{sandwich.patty} </p>
                <p className="left-side">Anzahl von Bulleten: </p><span>{sandwich.numberOfPatties} </span>
                <p className="left-side">Brot gegrillt: </p><span>{String(sandwich.grilled)} </span>
                <p className="left-side">Extrawünsche: </p><span>{sandwich.extraWishes} </span>
            </div>
        </section>
    )
}