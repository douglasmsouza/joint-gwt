var jointTransform = jointTransform || {};

jointTransform.util = {
	setPositions : function(graph, cell) {
		if (!cell.positionedByParent) {
			var connectedLinks = graph.getConnectedLinks(cell);
			var children = [];
			var childrenIndex = 0;
			for (var i = 0; i < connectedLinks.length; i++) {
				var el = connectedLinks[i];
				/*
				 * Se o source eh o proprio objeto cell da funcao, quer dizer
				 * que eh um filho
				 */
				if (graph.getCell(el.attributes.source) == cell) {
					children[childrenIndex] = graph.getCell(el.attributes.target);
					childrenIndex++;
				}
			}
			/*
			 * Se tem mais de um filho e todos sao folhas, organiza
			 * verticalmente
			 */
			if (children.length > 1) {
				/* Verifica se todos os filhos são folhas */
				var allLeaf = true;
				for (var i = 0; i < children.length; i++) {
					var child = children[i];
					if (graph.getConnectedLinks(child).length > 1) {
						allLeaf = false;
						break;
					}
				}
				/*
				 * Se todos os filhos são folhas, posiciona-os verticalmente
				 */
				if (allLeaf) {
					/*
					 * Alinha os filhos verticalmente, com base no left do pai
					 */
					var positionX = cell.attributes.position.x;
					var positionY = cell.attributes.position.y;
					var spacing = cell.attributes.size.height;
					for (var i = 0; i < children.length; i++) {
						var child = children[i];
						child.positionedByParent = true;
						/* Calcula a posicao do filho */
						child.set('position', {
							x : positionX + 40,
							y : positionY + spacing + 20
						});
						//						
						spacing = spacing + child.attributes.size.height + 20;
					}
				}
			}
			/* Verifica os filhos */
			for (var i = 0; i < children.length; i++) {
				jointTransform.util.setPositions(graph, children[i]);
			}
		}
	},
	setVertices : function(graph ,link, points) {
		var targetElement = graph.getCell(link.attributes.target);
		if (targetElement.positionedByParent) {
			var point = {
				x : targetElement.attributes.position.x - 20,
				y : targetElement.attributes.position.y + targetElement.attributes.size.height / 2
			};
			link.set('vertices', [point]);
		} else {
			link.set('vertices', points);
		}
	}
};