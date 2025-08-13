const $ = (sel) => document.querySelector(sel);

async function httpRequest(method, url, body) {
  const options = { method, headers: { 'Content-Type': 'application/json' } };
  if (body) options.body = JSON.stringify(body);
  const res = await fetch(url, options);
  const text = await res.text();
  let data;
  try { data = text ? JSON.parse(text) : null; } catch { data = text; }
  if (!res.ok) throw data;
  return data;
}

// User
async function createUser() {
  try {
    const dto = { name: $("#userName").value, email: $("#userEmail").value, address: $("#userAddress").value };
    const data = await httpRequest('POST', '/users', dto);
    $("#userResult").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#userResult").textContent = JSON.stringify(err, null, 2);
  }
}
async function fetchUsers() {
  try {
    const data = await httpRequest('GET', '/users');
    $("#usersList").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#usersList").textContent = JSON.stringify(err, null, 2);
  }
}

// Inventory
async function saveInventory() {
  try {
    const dto = { productName: $("#invProductName").value, stock: parseInt($("#invStock").value) };
    const data = await httpRequest('POST', '/inventory', dto);
    $("#invResult").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#invResult").textContent = JSON.stringify(err, null, 2);
  }
}
async function updateInventory() {
  try {
    const dto = { productName: $("#invProductName").value, stock: parseInt($("#invStock").value) };
    const data = await httpRequest('PUT', `/inventory/${$("#invId").value}`, dto);
    $("#invResult").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#invResult").textContent = JSON.stringify(err, null, 2);
  }
}
async function fetchInventory() {
  try {
    const data = await httpRequest('GET', '/inventory');
    $("#invList").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#invList").textContent = JSON.stringify(err, null, 2);
  }
}

// Order
async function createOrder() {
  try {
    const dto = { userId: parseInt($("#orderUserId").value), status: $("#orderStatus").value };
    const data = await httpRequest('POST', '/orders', dto);
    $("#orderResult").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#orderResult").textContent = JSON.stringify(err, null, 2);
  }
}
async function fetchOrders() {
  try {
    const data = await httpRequest('GET', '/orders');
    $("#ordersList").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#ordersList").textContent = JSON.stringify(err, null, 2);
  }
}
async function completeOrder() {
  try {
    const data = await httpRequest('PUT', `/orders/${$("#completeOrderId").value}/complete`);
    $("#statusResult").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#statusResult").textContent = JSON.stringify(err, null, 2);
  }
}

// Order Items
async function addOrderItem() {
  try {
    const totalPrice = parseFloat($("#itemUnitPrice").value) * parseInt($("#itemQuantity").value);
    const dto = {
      orderId: parseInt($("#itemOrderId").value),
      productName: $("#itemProductName").value,
      quantity: parseInt($("#itemQuantity").value),
      totalPrice
    };
    const data = await httpRequest('POST', '/order-items', dto);
    $("#itemResult").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#itemResult").textContent = JSON.stringify(err, null, 2);
  }
}
async function fetchOrderItems() {
  try {
    const data = await httpRequest('GET', '/order-items');
    $("#itemsList").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#itemsList").textContent = JSON.stringify(err, null, 2);
  }
}

// Payment
async function createPayment() {
  try {
    const dto = { orderId: parseInt($("#payOrderId").value), paidAmount: parseFloat($("#payAmount").value) };
    const data = await httpRequest('POST', '/payments', dto);
    $("#payResult").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#payResult").textContent = JSON.stringify(err, null, 2);
  }
}
async function fetchPayments() {
  try {
    const data = await httpRequest('GET', '/payments');
    $("#payList").textContent = JSON.stringify(data, null, 2);
  } catch (err) {
    $("#payList").textContent = JSON.stringify(err, null, 2);
  }
}
