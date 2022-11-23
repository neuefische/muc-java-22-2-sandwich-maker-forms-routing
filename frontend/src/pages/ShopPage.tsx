import CreateSandwich from "../components/CreateSandwich"
import SandwichOverview from "../components/SandwichOverview"
import { Sandwich } from "../model/Sandwich"

type ShopPageProps = {
    sandwiches: Sandwich []
    addSandwich(newSandwich: Sandwich): void
    deleteSandwich(id: string): void
}

export default function ShopPage(props: ShopPageProps) {

    return (
        <section>
            <h1>Bestellungen</h1>

            {
                props.sandwiches.length === 0
                &&
                <h3>Bitte bestellen Sie endlich mal, ich habe bald Feierabend und möchte nachhause
                    gehen</h3>
            }

            <SandwichOverview sandwiches={props.sandwiches} deleteSandwich={props.deleteSandwich}/>
            <CreateSandwich addSandwich={props.addSandwich}/>
        </section>
    )
}