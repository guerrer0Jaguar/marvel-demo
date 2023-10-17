const React = require('react'); 
const ReactDOM = require('react-dom'); 
const client = require('./client');

class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {characters: []};
	}

	componentDidMount() { 
		client({method: 'GET', path: 'http://localhost:8080/api/characters'}).done(response => {
			this.setState({characters: response.entity.data.results});
		});
	}

	render() { 
		return (
			<CharacterList characters={this.state.characters}/>
		)
	}		
}

class CharacterList extends React.Component{
	render() {
		const characters = this.props.characters.map(character =>
			<Character key={character.id} character={character}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Description</th>
					</tr>
					{characters}
				</tbody>
			</table>
		)
	}
}

class Character extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.character.id}</td>
				<td>{this.props.character.name}</td>
				<td>{this.props.character.description}</td>
			</tr>
		)
	}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)